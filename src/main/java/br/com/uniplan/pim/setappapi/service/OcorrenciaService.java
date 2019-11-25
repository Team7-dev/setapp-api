package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.OcorrenciaDto;
import br.com.uniplan.pim.setappapi.entity.Ocorrencia;
import br.com.uniplan.pim.setappapi.exception.FieldCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.FieldMustBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceNotFoundException;
import br.com.uniplan.pim.setappapi.repository.OcorrenciaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OcorrenciaService {

    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    public List<OcorrenciaDto> findAll() {
        List<OcorrenciaDto> ocorrenciasDto = new ArrayList<>();
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();
        for (Ocorrencia ocorrencia : ocorrencias) {
            ocorrenciasDto.add(createDtoFromEntity(ocorrencia));
        }
        return ocorrenciasDto;
    }

    public OcorrenciaDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id);
        if (ocorrencia == null) {
            throw new ResourceNotFoundException("ocorrencia", id.toString());
        }
        OcorrenciaDto ocorrenciaDto = createDtoFromEntity(ocorrencia);
        return ocorrenciaDto;
    }

    private OcorrenciaDto createDtoFromEntity(Ocorrencia ocorrencia) {
        OcorrenciaDto ocorrenciaDto = new OcorrenciaDto();
        ocorrenciaDto.setId(ocorrencia.getId());
        ocorrenciaDto.setDataHoraCadastro(ocorrencia.getDataHoraCadastro());
        ocorrenciaDto.setOcorrencia(ocorrencia.getOcorrencia());
        ocorrenciaDto.setDescricao(ocorrencia.getDescricao());
        ocorrenciaDto.setDataHoraOcorrencia(ocorrencia.getDataHoraOcorrencia());
        ocorrenciaDto.setDataHoraConclusao(ocorrencia.getDataHoraConclusao());
        ocorrenciaDto.setSituacao(ocorrencia.getSituacao());
        ocorrenciaDto.setUsuario(ocorrencia.getUsuario());
        return ocorrenciaDto;
    }

    public Long create(OcorrenciaDto ocorrenciaDto) {
        validateOnCreate(ocorrenciaDto);
        Ocorrencia ocorrencia = createEntityFromDto(ocorrenciaDto, false);
        return ocorrenciaRepository.persist(ocorrencia);
    }

    private Ocorrencia createEntityFromDto(OcorrenciaDto ocorrenciaDto, Boolean edicao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        if (edicao) {
            ocorrencia.setId(ocorrenciaDto.getId());
        }
        ocorrencia.setDataHoraCadastro(ocorrenciaDto.getDataHoraCadastro());
        ocorrencia.setOcorrencia(ocorrenciaDto.getOcorrencia());
        ocorrencia.setDescricao(ocorrenciaDto.getDescricao());
        ocorrencia.setDataHoraOcorrencia(ocorrenciaDto.getDataHoraOcorrencia());
        ocorrencia.setDataHoraConclusao(ocorrenciaDto.getDataHoraConclusao());
        ocorrencia.setSituacao(ocorrenciaDto.getSituacao());
        ocorrencia.setUsuario(ocorrenciaDto.getUsuario());
        return ocorrencia;
    }

    private void validateOnCreate(OcorrenciaDto ocorrenciaDto) {
        if (ocorrenciaDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (ocorrenciaDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (ocorrenciaDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(ocorrenciaDto.getOcorrencia())) {
            throw new FieldCannotBeNullException("ocorrencia");
        }
        if (StringUtils.isBlank(ocorrenciaDto.getDescricao())) {
            throw new FieldCannotBeNullException("descricao");
        }
        if (ocorrenciaDto.getDataHoraOcorrencia() == null) {
            throw new FieldCannotBeNullException("dataHoraOcorrencia");
        }
        if (StringUtils.isBlank(ocorrenciaDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (ocorrenciaDto.getUsuario() == null) {
            throw new FieldCannotBeNullException("usuario");
        }
    }

    public void update(OcorrenciaDto ocorrenciaDto) {
        validateOnUpdate(ocorrenciaDto);
        Ocorrencia ocorrencia = createEntityFromDto(ocorrenciaDto, true);
        ocorrenciaRepository.merge(ocorrencia);
    }

    private void validateOnUpdate(OcorrenciaDto ocorrenciaDto) {
        if (ocorrenciaDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (ocorrenciaDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(ocorrenciaDto.getId());
        if (ocorrencia == null) {
            throw new ResourceNotFoundException("ocorrencia", ocorrenciaDto.getId().toString());
        }
        if (ocorrenciaDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(ocorrenciaDto.getOcorrencia())) {
            throw new FieldCannotBeNullException("ocorrencia");
        }
        if (StringUtils.isBlank(ocorrenciaDto.getDescricao())) {
            throw new FieldCannotBeNullException("descricao");
        }
        if (ocorrenciaDto.getDataHoraOcorrencia() == null) {
            throw new FieldCannotBeNullException("dataHoraOcorrencia");
        }
        if (StringUtils.isBlank(ocorrenciaDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (ocorrenciaDto.getUsuario() == null) {
            throw new FieldCannotBeNullException("usuario");
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id);
        if (ocorrencia == null) {
            throw new ResourceNotFoundException("ocorrencia", id.toString());
        }
        ocorrenciaRepository.remove(ocorrencia);
    }

}
