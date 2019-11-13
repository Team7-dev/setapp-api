package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.UnidadeDto;
import br.com.uniplan.pim.setappapi.entity.Unidade;
import br.com.uniplan.pim.setappapi.exception.FieldCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.FieldMustBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceNotFoundException;
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
            unidadesDto.add(createUnidadeDtoFromUnidadeEntity(unidade));
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
        UnidadeDto unidadeDto = createUnidadeDtoFromUnidadeEntity(unidade);
        return unidadeDto;
    }

    private UnidadeDto createUnidadeDtoFromUnidadeEntity(Unidade unidade) {
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
        Unidade unidade = createUnidadeEntityFromUnidadeDto(unidadeDto, false);
        return unidadeRepository.persist(unidade);
    }

    private Unidade createUnidadeEntityFromUnidadeDto(UnidadeDto unidadeDto, Boolean edicao) {
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
        if (unidadeDto.getDataHoraCadastro() != null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(unidadeDto.getBloco())) {
            throw new FieldCannotBeNullException("bloco");
        }
        if (unidadeDto.getNumero() != null) {
            throw new FieldCannotBeNullException("numero");
        }
        if (StringUtils.isBlank(unidadeDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (unidadeDto.getUsuario() == null) {
            throw new FieldCannotBeNullException("usuario");
        }
    }

    public void update(UnidadeDto unidadeDto) {
        validateOnUpdate(unidadeDto);
        Unidade unidade = createUnidadeEntityFromUnidadeDto(unidadeDto, true);
        unidadeRepository.merge(unidade);
    }

    private void validateOnUpdate(UnidadeDto unidadeDto) {
        if (unidadeDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (unidadeDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        if (unidadeDto.getDataHoraCadastro() != null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        Unidade unidade = unidadeRepository.findById(unidadeDto.getId());
        if (unidade == null) {
            throw new ResourceNotFoundException("unidade", unidadeDto.getId().toString());
        }
        if (StringUtils.isBlank(unidadeDto.getBloco())) {
            throw new FieldCannotBeNullException("bloco");
        }
        if (unidadeDto.getNumero() != null) {
            throw new FieldCannotBeNullException("numero");
        }
        if (StringUtils.isBlank(unidadeDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (unidadeDto.getUsuario() == null) {
            throw new FieldCannotBeNullException("usuario");
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
}
