package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.VisitanteDto;
import br.com.uniplan.pim.setappapi.entity.Visitante;
import br.com.uniplan.pim.setappapi.exception.*;
import br.com.uniplan.pim.setappapi.repository.VisitanteRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitanteService {

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
        visitanteDto.setId(visitante.getId());
        visitanteDto.setNome(visitante.getNome());
        visitanteDto.setCpf(visitante.getCpf());
        visitanteDto.setBloco(visitante.getBloco());
        visitanteDto.setApartamento(visitante.getApartamento());
        return visitanteDto;
    }

    public Long create(VisitanteDto visitanteDto) {
        validateOnCreate(visitanteDto);
        Visitante visitante = createVisitanteEntityFromVisitanteDto(visitanteDto, false);
        return visitanteRepository.persist(visitante);
    }

    private Visitante createVisitanteEntityFromVisitanteDto(VisitanteDto visitanteDto, Boolean edicao) {
        Visitante visitante = new Visitante();
        if(edicao) {
            visitante.setId(visitanteDto.getId());
        }
        visitante.setNome(visitanteDto.getNome());
        visitante.setCpf(visitanteDto.getCpf());
        visitante.setBloco(visitanteDto.getBloco());
        visitante.setApartamento(visitanteDto.getApartamento());
        return visitante;
    }

    private void validateOnCreate(VisitanteDto visitanteDto) {
        if (visitanteDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (visitanteDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (StringUtils.isBlank(visitanteDto.getNome())) {
            throw new FieldCannotBeNullException("nome");
        } else {
            Long count = visitanteRepository.countVisitantesByName(visitanteDto.getNome());
            if (count > 0) {
                throw new UniqueFieldContraintException("nome");
            }
        }
        if (StringUtils.isBlank(visitanteDto.getCpf())) {
            throw new FieldCannotBeNullException("cpf");
        }
        if (StringUtils.isBlank(visitanteDto.getBloco())) {
            throw new FieldCannotBeNullException("bloco");
        }
        if (visitanteDto.getApartamento() == null) {
            throw new FieldCannotBeNullException("apartamento");
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
            Long count = visitanteRepository.countVisitantesByNameExceptWithId(visitanteDto.getNome(), visitanteDto.getId());
            if (count > 0) {
                throw new UniqueFieldContraintException("nome");
            }
        }
        if (StringUtils.isBlank(visitanteDto.getCpf())) {
            throw new FieldCannotBeNullException("cpf");
        }
        if (StringUtils.isBlank(visitanteDto.getBloco())) {
            throw new FieldCannotBeNullException("bloco");
        }
        if (visitanteDto.getApartamento() == null) {
            throw new FieldCannotBeNullException("apartamento");
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
