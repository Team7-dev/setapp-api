package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.AssembleiaDto;
import br.com.uniplan.pim.setappapi.entity.Assembleia;
import br.com.uniplan.pim.setappapi.exception.FieldCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.FieldMustBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceNotFoundException;
import br.com.uniplan.pim.setappapi.repository.AssembleiaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssembleiaService {

    private AssembleiaRepository assembleiaRepository;

    @Autowired
    public AssembleiaService(AssembleiaRepository assembleiaRepository) {
        this.assembleiaRepository = assembleiaRepository;
    }

    public List<AssembleiaDto> findAll() {
        List<AssembleiaDto> assembleiasDto = new ArrayList<>();
        List<Assembleia> assembleias = assembleiaRepository.findAll();
        for (Assembleia assembleia : assembleias) {
            assembleiasDto.add(createDtoFromEntity(assembleia));
        }
        return assembleiasDto;
    }

    public AssembleiaDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Assembleia assembleia = assembleiaRepository.findById(id);
        if (assembleia == null) {
            throw new ResourceNotFoundException("assembleia", id.toString());
        }
        AssembleiaDto assembleiaDto = createDtoFromEntity(assembleia);
        return assembleiaDto;
    }

    private AssembleiaDto createDtoFromEntity(Assembleia assembleia) {
        AssembleiaDto assembleiaDto = new AssembleiaDto();
        assembleiaDto.setId(assembleia.getId());
        assembleiaDto.setDataHoraCadastro(assembleia.getDataHoraCadastro());
        assembleiaDto.setMotivo(assembleia.getMotivo());
        assembleiaDto.setDataHoraAgendamento(assembleia.getDataHoraAgendamento());
        assembleiaDto.setDataHoraConclusao(assembleia.getDataHoraConclusao());
        assembleiaDto.setSituacao(assembleia.getSituacao());
        assembleiaDto.setUsuario(assembleia.getUsuario());
        return assembleiaDto;
    }

    public Long create(AssembleiaDto assembleiaDto) {
        validateOnCreate(assembleiaDto);
        Assembleia assembleia = createEntityFromDto(assembleiaDto, false);
        return assembleiaRepository.persist(assembleia);
    }

    private Assembleia createEntityFromDto(AssembleiaDto assembleiaDto, Boolean edicao) {
        Assembleia assembleia = new Assembleia();
        if (edicao) {
            assembleia.setId(assembleiaDto.getId());
        }
        assembleia.setDataHoraCadastro(assembleiaDto.getDataHoraCadastro());
        assembleia.setMotivo(assembleiaDto.getMotivo());
        assembleia.setDataHoraAgendamento(assembleiaDto.getDataHoraAgendamento());
        assembleia.setDataHoraConclusao(assembleiaDto.getDataHoraConclusao());
        assembleia.setSituacao(assembleiaDto.getSituacao());
        assembleia.setUsuario(assembleiaDto.getUsuario());
        return assembleia;
    }

    private void validateOnCreate(AssembleiaDto assembleiaDto) {
        if (assembleiaDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (assembleiaDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (assembleiaDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(assembleiaDto.getMotivo())) {
            throw new FieldCannotBeNullException("motivo");
        }
        if (assembleiaDto.getDataHoraAgendamento() == null) {
            throw new FieldCannotBeNullException("dataHoraAgendamento");
        }
        if (StringUtils.isBlank(assembleiaDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (assembleiaDto.getUsuario() == null) {
            throw new FieldCannotBeNullException("usuario");
        }
    }

    public void update(AssembleiaDto assembleiaDto) {
        validateOnUpdate(assembleiaDto);
        Assembleia assembleia = createEntityFromDto(assembleiaDto, true);
        assembleiaRepository.merge(assembleia);
    }

    private void validateOnUpdate(AssembleiaDto assembleiaDto) {
        if (assembleiaDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (assembleiaDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        Assembleia assembleia = assembleiaRepository.findById(assembleiaDto.getId());
        if (assembleia == null) {
            throw new ResourceNotFoundException("assembleia", assembleiaDto.getId().toString());
        }
        if (assembleiaDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(assembleiaDto.getMotivo())) {
            throw new FieldCannotBeNullException("descricao");
        }
        if (assembleiaDto.getDataHoraAgendamento() == null) {
            throw new FieldCannotBeNullException("dataHoraAgendamento");
        }
        if (StringUtils.isBlank(assembleiaDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (assembleiaDto.getUsuario() == null) {
            throw new FieldCannotBeNullException("usuario");
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Assembleia assembleia = assembleiaRepository.findById(id);
        if (assembleia == null) {
            throw new ResourceNotFoundException("assembleia", id.toString());
        }
        assembleiaRepository.remove(assembleia);
    }

    public List<AssembleiaDto> findPendant() {
        List<AssembleiaDto> assembleiasDto = new ArrayList<>();
        List<Assembleia> assembleias = assembleiaRepository.findPendant();
        for (Assembleia assembleia : assembleias) {
            assembleiasDto.add(createDtoFromEntity(assembleia));
        }
        return assembleiasDto;
    }

    public List<AssembleiaDto> findDone() {
        List<AssembleiaDto> assembleiasDto = new ArrayList<>();
        List<Assembleia> assembleias = assembleiaRepository.findDone();
        for (Assembleia assembleia : assembleias) {
            assembleiasDto.add(createDtoFromEntity(assembleia));
        }
        return assembleiasDto;
    }

}
