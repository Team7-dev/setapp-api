package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.AreaReservaDto;
import br.com.uniplan.pim.setappapi.entity.AreaReserva;
import br.com.uniplan.pim.setappapi.exception.FieldCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.FieldMustBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceNotFoundException;
import br.com.uniplan.pim.setappapi.repository.AreaReservaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaReservaService {

    private AreaReservaRepository areaReservaRepository;

    @Autowired
    public AreaReservaService(AreaReservaRepository areaReservaRepository) {
        this.areaReservaRepository = areaReservaRepository;
    }

    public List<AreaReservaDto> findAll() {
        List<AreaReservaDto> areaReservasDto = new ArrayList<>();
        List<AreaReserva> areaReservas = areaReservaRepository.findAll();
        for (AreaReserva areaReserva : areaReservas) {
            areaReservasDto.add(createDtoFromEntity(areaReserva));
        }
        return areaReservasDto;
    }

    public AreaReservaDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        AreaReserva areaReserva = areaReservaRepository.findById(id);
        if (areaReserva == null) {
            throw new ResourceNotFoundException("areaReserva", id.toString());
        }
        AreaReservaDto areaReservaDto = createDtoFromEntity(areaReserva);
        return areaReservaDto;
    }

    private AreaReservaDto createDtoFromEntity(AreaReserva areaReserva) {
        AreaReservaDto areaReservaDto = new AreaReservaDto();
        areaReservaDto.setId(areaReserva.getId());
        areaReservaDto.setArea(areaReserva.getArea());
        areaReservaDto.setDescricao(areaReserva.getDescricao());
        return areaReservaDto;
    }

    private AreaReserva createEntityFromDto(AreaReservaDto areaReservaDto, Boolean edicao) {
        AreaReserva areaReserva = new AreaReserva();
        if (edicao) {
            areaReserva.setId(areaReservaDto.getId());
        }
        areaReserva.setArea(areaReservaDto.getArea());
        areaReserva.setDescricao(areaReservaDto.getDescricao());
        return areaReserva;
    }

}
