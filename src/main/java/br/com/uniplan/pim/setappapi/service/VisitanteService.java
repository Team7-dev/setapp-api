package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.VisitanteDto;
import br.com.uniplan.pim.setappapi.entity.Unidade;
import br.com.uniplan.pim.setappapi.entity.Visitante;
import br.com.uniplan.pim.setappapi.exception.*;
import br.com.uniplan.pim.setappapi.repository.VisitanteRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitanteService {

    @Autowired
    public UnidadeService unidadeService;
    private VisitanteRepository visitanteRepository;

    @Autowired
    public VisitanteService(VisitanteRepository visitanteRepository) {
        this.visitanteRepository = visitanteRepository;
    }

    public List<VisitanteDto> findAll() {
        List<VisitanteDto> visitantesDto = new ArrayList<>();
        List<Visitante> visitantes = visitanteRepository.findAll();
        for (Visitante visitante : visitantes) {
            visitantesDto.add(createVisitanteDtoFromVisitanteEntity(visitante));
        }
        return visitantesDto;
    }

    public VisitanteDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Visitante visitante = visitanteRepository.findById(id);
        if (visitante == null) {
            throw new ResourceNotFoundException("visitante", id.toString());
        }
        VisitanteDto visitanteDto = createVisitanteDtoFromVisitanteEntity(visitante);
        return visitanteDto;
    }

    private VisitanteDto createVisitanteDtoFromVisitanteEntity(Visitante visitante) {
        VisitanteDto visitanteDto = new VisitanteDto();
        visitanteDto.setDataHoraCadastro(visitante.getDataHoraCadastro());
        visitanteDto.setId(visitante.getId());
        visitanteDto.setNome(visitante.getNome());
        visitanteDto.setCpf(visitante.getCpf());
        visitanteDto.setSituacao(visitante.getSituacao());
        visitanteDto.setUnidade(visitante.getUnidade());
        return visitanteDto;
    }

    public Long create(VisitanteDto visitanteDto) {
        validateOnCreate(visitanteDto);
        Visitante visitante = createVisitanteEntityFromVisitanteDto(visitanteDto, false);
        return visitanteRepository.persist(visitante);
    }

    private Visitante createVisitanteEntityFromVisitanteDto(VisitanteDto visitanteDto, Boolean edicao) {
        Visitante visitante = new Visitante();
        visitante.setDataHoraCadastro(visitanteDto.getDataHoraCadastro());
        if (edicao) {
            visitante.setId(visitanteDto.getId());
        }
        visitante.setNome(visitanteDto.getNome());
        visitante.setCpf(visitanteDto.getCpf());
        visitante.setSituacao(visitanteDto.getSituacao());
        Unidade unidade = unidadeService.findByBlocoApartamento(visitanteDto.getUnidade().getBloco(), visitanteDto.getUnidade().getNumero());
        if (unidade != null) {
            visitante.setUnidade(unidade);
        } else {
            throw new ResourceNotFoundException("Não foi possível encontrar o apartarmento", visitanteDto.getUnidade().getBloco() + "-" + visitanteDto.getUnidade().getNumero());
        }
        return visitante;
    }

    private void validateOnCreate(VisitanteDto visitanteDto) {
        if (visitanteDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (visitanteDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (visitanteDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (StringUtils.isBlank(visitanteDto.getNome())) {
            throw new FieldCannotBeNullException("nome");
        } else {
            Long count = visitanteRepository.countByName(visitanteDto.getNome());
            if (count > 0) {
                throw new UniqueFieldContraintException("nome");
            }
        }
        if (StringUtils.isBlank(visitanteDto.getCpf())) {
            throw new FieldCannotBeNullException("cpf");
        }
        if (StringUtils.isBlank(visitanteDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (visitanteDto.getUnidade() != null) {
            if (StringUtils.isBlank(visitanteDto.getUnidade().getBloco())) {
                throw new FieldCannotBeNullException("bloco");
            } else if (visitanteDto.getUnidade().getNumero() == null) {
                throw new FieldCannotBeNullException("numero");
            }
        } else {
            throw new FieldCannotBeNullException("bloco e numero");
        }
    }

    public void update(VisitanteDto visitanteDto) {
        validateOnUpdate(visitanteDto);
        Visitante visitante = createVisitanteEntityFromVisitanteDto(visitanteDto, true);
        visitanteRepository.merge(visitante);
    }

    private void validateOnUpdate(VisitanteDto visitanteDto) {
        if (visitanteDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (visitanteDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (visitanteDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        Visitante visitante = visitanteRepository.findById(visitanteDto.getId());
        if (visitante == null) {
            throw new ResourceNotFoundException("visitante", visitanteDto.getId().toString());
        }
        if (StringUtils.isBlank(visitanteDto.getNome())) {
            throw new FieldCannotBeNullException("nome");
        } else {
            Long count = visitanteRepository.countByNameExceptWithId(visitanteDto.getNome(), visitanteDto.getId());
            if (count > 0) {
                throw new UniqueFieldContraintException("nome");
            }
        }
        if (StringUtils.isBlank(visitanteDto.getCpf())) {
            throw new FieldCannotBeNullException("cpf");
        }
        if (StringUtils.isBlank(visitanteDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (visitanteDto.getUnidade() == null) {
            throw new FieldCannotBeNullException("unidade");
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Visitante visitante = visitanteRepository.findById(id);
        if (visitante == null) {
            throw new ResourceNotFoundException("visitante", id.toString());
        }
        visitanteRepository.remove(visitante);
    }

}
