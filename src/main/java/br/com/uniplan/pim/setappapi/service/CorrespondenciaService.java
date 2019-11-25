package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.CorrespondenciaDto;
import br.com.uniplan.pim.setappapi.entity.Correspondencia;
import br.com.uniplan.pim.setappapi.exception.FieldCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.FieldMustBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceNotFoundException;
import br.com.uniplan.pim.setappapi.repository.CorrespondenciaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CorrespondenciaService {

    private CorrespondenciaRepository correspondenciaRepository;

    @Autowired
    public CorrespondenciaService(CorrespondenciaRepository correspondenciaRepository) {
        this.correspondenciaRepository = correspondenciaRepository;
    }

    public List<CorrespondenciaDto> findAll() {
        List<CorrespondenciaDto> correspondenciasDto = new ArrayList<>();
        List<Correspondencia> correspondencias = correspondenciaRepository.findAll();
        for (Correspondencia correspondencia : correspondencias) {
            correspondenciasDto.add(createDtoFromEntity(correspondencia));
        }
        return correspondenciasDto;
    }

    public CorrespondenciaDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Correspondencia correspondencia = correspondenciaRepository.findById(id);
        if (correspondencia == null) {
            throw new ResourceNotFoundException("correspondencia", id.toString());
        }
        CorrespondenciaDto correspondenciaDto = createDtoFromEntity(correspondencia);
        return correspondenciaDto;
    }

    private CorrespondenciaDto createDtoFromEntity(Correspondencia correspondencia) {
        CorrespondenciaDto correspondenciaDto = new CorrespondenciaDto();
        correspondenciaDto.setId(correspondencia.getId());
        correspondenciaDto.setDataHoraCadastro(correspondencia.getDataHoraCadastro());
        correspondenciaDto.setDescricao(correspondencia.getDescricao());
        correspondenciaDto.setDataHoraRecebida(correspondencia.getDataHoraRecebida());
        correspondenciaDto.setDataHoraRetirada(correspondencia.getDataHoraRetirada());
        correspondenciaDto.setSituacao(correspondencia.getSituacao());
        correspondenciaDto.setUsuario(correspondencia.getUsuario());
        return correspondenciaDto;
    }

    public Long create(CorrespondenciaDto correspondenciaDto) {
        validateOnCreate(correspondenciaDto);
        Correspondencia correspondencia = createEntityFromDto(correspondenciaDto, false);
        return correspondenciaRepository.persist(correspondencia);
    }

    private Correspondencia createEntityFromDto(CorrespondenciaDto correspondenciaDto, Boolean edicao) {
        Correspondencia correspondencia = new Correspondencia();
        if (edicao) {
            correspondencia.setId(correspondenciaDto.getId());
        }
        correspondencia.setDataHoraCadastro(correspondenciaDto.getDataHoraCadastro());
        correspondencia.setDescricao(correspondenciaDto.getDescricao());
        correspondencia.setDataHoraRecebida(correspondenciaDto.getDataHoraRecebida());
        correspondencia.setDataHoraRetirada(correspondenciaDto.getDataHoraRetirada());
        correspondencia.setSituacao(correspondenciaDto.getSituacao());
        correspondencia.setUsuario(correspondenciaDto.getUsuario());
        return correspondencia;
    }

    private void validateOnCreate(CorrespondenciaDto correspondenciaDto) {
        if (correspondenciaDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (correspondenciaDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (correspondenciaDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(correspondenciaDto.getDescricao())) {
            throw new FieldCannotBeNullException("descricao");
        }
        if (correspondenciaDto.getDataHoraRecebida() == null) {
            throw new FieldCannotBeNullException("dataHoraRecebida");
        }
        if (StringUtils.isBlank(correspondenciaDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
    }

    public void update(CorrespondenciaDto correspondenciaDto) {
        validateOnUpdate(correspondenciaDto);
        Correspondencia correspondencia = createEntityFromDto(correspondenciaDto, true);
        correspondenciaRepository.merge(correspondencia);
    }

    private void validateOnUpdate(CorrespondenciaDto correspondenciaDto) {
        if (correspondenciaDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (correspondenciaDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        Correspondencia correspondencia = correspondenciaRepository.findById(correspondenciaDto.getId());
        if (correspondencia == null) {
            throw new ResourceNotFoundException("correspondencia", correspondenciaDto.getId().toString());
        }
        if (correspondenciaDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(correspondenciaDto.getDescricao())) {
            throw new FieldCannotBeNullException("descricao");
        }
        if (correspondenciaDto.getDataHoraRecebida() == null) {
            throw new FieldCannotBeNullException("dataHoraRecebida");
        }
        if (StringUtils.isBlank(correspondenciaDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Correspondencia correspondencia = correspondenciaRepository.findById(id);
        if (correspondencia == null) {
            throw new ResourceNotFoundException("correspondencia", id.toString());
        }
        correspondenciaRepository.remove(correspondencia);
    }

    public Correspondencia findByBlocoApartamento(String bloco, Integer numero) {
        return correspondenciaRepository.findByBlocoApartamento(bloco, numero);
    }

    public List<CorrespondenciaDto> findPendant() {
        List<CorrespondenciaDto> correspondenciasDto = new ArrayList<>();
        List<Correspondencia> correspondencias = correspondenciaRepository.findPendant();
        for (Correspondencia correspondencia : correspondencias) {
            correspondenciasDto.add(createDtoFromEntity(correspondencia));
        }
        return correspondenciasDto;
    }

    public List<CorrespondenciaDto> findRemoval() {
        List<CorrespondenciaDto> correspondenciasDto = new ArrayList<>();
        List<Correspondencia> correspondencias = correspondenciaRepository.findRemoval();
        for (Correspondencia correspondencia : correspondencias) {
            correspondenciasDto.add(createDtoFromEntity(correspondencia));
        }
        return correspondenciasDto;
    }

}
