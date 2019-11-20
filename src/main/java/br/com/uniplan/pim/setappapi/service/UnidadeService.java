package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.UnidadeDto;
import br.com.uniplan.pim.setappapi.entity.Unidade;
import br.com.uniplan.pim.setappapi.exception.*;
import br.com.uniplan.pim.setappapi.repository.UnidadeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnidadeService {

    private UnidadeRepository unidadeRepository;

    @Autowired
    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public List<UnidadeDto> findAll() {
        List<UnidadeDto> unidadesDto = new ArrayList<>();
        List<Unidade> unidades = unidadeRepository.findAll();
        for (Unidade unidade : unidades) {
            unidadesDto.add(createDtoFromEntity(unidade));
        }
        return unidadesDto;
    }

    public UnidadeDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Unidade unidade = unidadeRepository.findById(id);
        if (unidade == null) {
            throw new ResourceNotFoundException("unidade", id.toString());
        }
        UnidadeDto unidadeDto = createDtoFromEntity(unidade);
        return unidadeDto;
    }

    private UnidadeDto createDtoFromEntity(Unidade unidade) {
        UnidadeDto unidadeDto = new UnidadeDto();
        unidadeDto.setId(unidade.getId());
        unidadeDto.setDataHoraCadastro(unidade.getDataHoraCadastro());
        unidadeDto.setBloco(unidade.getBloco());
        unidadeDto.setNumero(unidade.getNumero());
        unidadeDto.setSituacao(unidade.getSituacao());
        unidadeDto.setUsuario(unidade.getUsuario());
        return unidadeDto;
    }

    public Long create(UnidadeDto unidadeDto) {
        validateOnCreate(unidadeDto);
        Unidade unidade = createEntityFromDto(unidadeDto, false);
        return unidadeRepository.persist(unidade);
    }

    private Unidade createEntityFromDto(UnidadeDto unidadeDto, Boolean edicao) {
        Unidade unidade = new Unidade();
        if (edicao) {
            unidade.setId(unidadeDto.getId());
        }
        unidade.setDataHoraCadastro(unidadeDto.getDataHoraCadastro());
        unidade.setBloco(unidadeDto.getBloco());
        unidade.setNumero(unidadeDto.getNumero());
        unidade.setSituacao(unidadeDto.getSituacao());
        unidade.setUsuario(unidadeDto.getUsuario());
        return unidade;
    }

    private void validateOnCreate(UnidadeDto unidadeDto) {
        if (unidadeDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (unidadeDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (unidadeDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(unidadeDto.getBloco())) {
            throw new FieldCannotBeNullException("bloco");
        }
        if (unidadeDto.getNumero() == null) {
            throw new FieldCannotBeNullException("numero");
        }
        if (StringUtils.isNotBlank(unidadeDto.getBloco()) && unidadeDto.getNumero() != null) {
            long count = unidadeRepository.countByBlocoUnidade(unidadeDto.getBloco(), unidadeDto.getNumero());
            if (count > 0) {
                throw new UniqueFieldContraintException("bloco e numero");
            }
        }
        if (StringUtils.isBlank(unidadeDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        } else if ("OCUPADO".equals(unidadeDto.getSituacao())) {
            if (unidadeDto.getUsuario() == null) {
                throw new FieldCannotBeNullException("usuario");
            } else {
                long count = unidadeRepository.countByUsuario(unidadeDto.getUsuario().getId());
                if (count > 0) {
                    throw new UniqueFieldContraintException("usuario");
                }
            }
        }
    }

    public void update(UnidadeDto unidadeDto) {
        validateOnUpdate(unidadeDto);
        Unidade unidade = createEntityFromDto(unidadeDto, true);
        unidadeRepository.merge(unidade);
    }

    private void validateOnUpdate(UnidadeDto unidadeDto) {
        if (unidadeDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (unidadeDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        Unidade unidade = unidadeRepository.findById(unidadeDto.getId());
        if (unidade == null) {
            throw new ResourceNotFoundException("unidade", unidadeDto.getId().toString());
        }
        if (unidadeDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(unidadeDto.getBloco())) {
            throw new FieldCannotBeNullException("bloco");
        }
        if (unidadeDto.getNumero() == null) {
            throw new FieldCannotBeNullException("numero");
        }
        if (StringUtils.isNotBlank(unidadeDto.getBloco()) && unidadeDto.getNumero() != null) {
            long count = unidadeRepository.countByBlocoUnidadeExceptWithId(unidadeDto.getBloco(), unidadeDto.getNumero(), unidadeDto.getId());
            if (count > 0) {
                throw new UniqueFieldContraintException("bloco e numero");
            }
        }
        if (StringUtils.isBlank(unidadeDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        } else if ("OCUPADO".equals(unidadeDto.getSituacao())) {
            if (unidadeDto.getUsuario() == null) {
                throw new FieldCannotBeNullException("usuario");
            } else {
                long count = unidadeRepository.countByUsuario(unidadeDto.getUsuario().getId());
                if (count > 0) {
                    throw new UniqueFieldContraintException("usuario");
                }
            }
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Unidade unidade = unidadeRepository.findById(id);
        if (unidade == null) {
            throw new ResourceNotFoundException("unidade", id.toString());
        }
        unidadeRepository.remove(unidade);
    }

    public Unidade findByBlocoApartamento(String bloco, Integer numero) {
        return unidadeRepository.findByBlocoApartamento(bloco, numero);
    }

    public List<UnidadeDto> findUnoccupied() {
        List<UnidadeDto> unidadesDto = new ArrayList<>();
        List<Unidade> unidades = unidadeRepository.findUnoccupied();
        for (Unidade unidade : unidades) {
            unidadesDto.add(createDtoFromEntity(unidade));
        }
        return unidadesDto;
    }

    public List<UnidadeDto> findOccupied() {
        List<UnidadeDto> unidadesDto = new ArrayList<>();
        List<Unidade> unidades = unidadeRepository.findOccupied();
        for (Unidade unidade : unidades) {
            unidadesDto.add(createDtoFromEntity(unidade));
        }
        return unidadesDto;
    }

}
