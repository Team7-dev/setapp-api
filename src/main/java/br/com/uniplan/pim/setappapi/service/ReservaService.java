package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.ReservaDto;
import br.com.uniplan.pim.setappapi.entity.Reserva;
import br.com.uniplan.pim.setappapi.exception.*;
import br.com.uniplan.pim.setappapi.repository.AreaReservaRepository;
import br.com.uniplan.pim.setappapi.repository.ReservaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {
    
    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<ReservaDto> findAll() {
        List<ReservaDto> reservasDto = new ArrayList<>();
        List<Reserva> reservas = reservaRepository.findAll();
        for (Reserva reserva : reservas) {
            reservasDto.add(createDtoFromEntity(reserva));
        }
        return reservasDto;
    }

    public ReservaDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Reserva reserva = reservaRepository.findById(id);
        if (reserva == null) {
            throw new ResourceNotFoundException("reserva", id.toString());
        }
        ReservaDto reservaDto = createDtoFromEntity(reserva);
        return reservaDto;
    }

    private ReservaDto createDtoFromEntity(Reserva reserva) {
        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setId(reserva.getId());
        reservaDto.setDataHoraCadastro(reserva.getDataHoraCadastro());
        reservaDto.setDataHoraInicio(reserva.getDataHoraInicio());
        reservaDto.setDataHoraFim(reserva.getDataHoraFim());
        reservaDto.setSituacao(reserva.getSituacao());
        reservaDto.setAreaReserva(reserva.getAreaReserva());
        reservaDto.setUsuario(reserva.getUsuario());
        return reservaDto;
    }

    public Long create(ReservaDto reservaDto) {
        validateOnCreate(reservaDto);
        Reserva reserva = createEntityFromDto(reservaDto, false);
        return reservaRepository.persist(reserva);
    }

    private Reserva createEntityFromDto(ReservaDto reservaDto, Boolean edicao) {
        Reserva reserva = new Reserva();
        if (edicao) {
            reserva.setId(reservaDto.getId());
        }
        reserva.setDataHoraCadastro(reservaDto.getDataHoraCadastro());
        reserva.setDataHoraInicio(reservaDto.getDataHoraInicio());
        reserva.setDataHoraFim(reservaDto.getDataHoraFim());
        reserva.setSituacao(reservaDto.getSituacao());
        reserva.setAreaReserva(reservaDto.getAreaReserva());
        reserva.setUsuario(reservaDto.getUsuario());
        return reserva;
    }

    private void validateOnCreate(ReservaDto reservaDto) {
        if (reservaDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (reservaDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (reservaDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (reservaDto.getDataHoraInicio() == null) {
            throw new FieldCannotBeNullException("dataHoraInicio");
        }
        if (reservaDto.getDataHoraFim() == null) {
            throw new FieldCannotBeNullException("dataHoraFim");
        }
        if (StringUtils.isBlank(reservaDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (reservaDto.getAreaReserva() == null) {
            throw new FieldCannotBeNullException("areaReserva");
        }
        if (reservaDto.getUsuario() == null) {
            throw new FieldCannotBeNullException("usuario");
        }
    }

    public void update(ReservaDto reservaDto) {
        validateOnUpdate(reservaDto);
        Reserva reserva = createEntityFromDto(reservaDto, true);
        reservaRepository.merge(reserva);
    }

    private void validateOnUpdate(ReservaDto reservaDto) {
        if (reservaDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (reservaDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        Reserva reserva = reservaRepository.findById(reservaDto.getId());
        if (reserva == null) {
            throw new ResourceNotFoundException("reserva", reservaDto.getId().toString());
        }
        if (reservaDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (reservaDto.getDataHoraInicio() == null) {
            throw new FieldCannotBeNullException("dataHoraInicio");
        }
        if (reservaDto.getDataHoraFim() == null) {
            throw new FieldCannotBeNullException("dataHoraFim");
        }

        if (StringUtils.isBlank(reservaDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (reservaDto.getAreaReserva() == null) {
            throw new FieldCannotBeNullException("areaReserva");
        }
        if (reservaDto.getUsuario() == null) {
            throw new FieldCannotBeNullException("usuario");
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Reserva reserva = reservaRepository.findById(id);
        if (reserva == null) {
            throw new ResourceNotFoundException("reserva", id.toString());
        }
        reservaRepository.remove(reserva);
    }

}
