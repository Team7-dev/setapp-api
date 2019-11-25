package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.BoletoDto;
import br.com.uniplan.pim.setappapi.entity.Boleto;
import br.com.uniplan.pim.setappapi.exception.FieldCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.FieldMustBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceNotFoundException;
import br.com.uniplan.pim.setappapi.repository.BoletoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoletoService {

    private BoletoRepository boletoRepository;

    @Autowired
    public BoletoService(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    public List<BoletoDto> findAll() {
        List<BoletoDto> boletosDto = new ArrayList<>();
        List<Boleto> boletos = boletoRepository.findAll();
        for (Boleto boleto : boletos) {
            boletosDto.add(createDtoFromEntity(boleto));
        }
        return boletosDto;
    }

    public BoletoDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Boleto boleto = boletoRepository.findById(id);
        if (boleto == null) {
            throw new ResourceNotFoundException("boleto", id.toString());
        }
        BoletoDto boletoDto = createDtoFromEntity(boleto);
        return boletoDto;
    }

    private BoletoDto createDtoFromEntity(Boleto boleto) {
        BoletoDto boletoDto = new BoletoDto();
        boletoDto.setId(boleto.getId());
        boletoDto.setDataHoraCadastro(boleto.getDataHoraCadastro());
        boletoDto.setArquivo(boleto.getArquivo());
        boletoDto.setSituacao(boleto.getSituacao());
        boletoDto.setUsuario(boleto.getUsuario());
        return boletoDto;
    }

    public Long create(BoletoDto boletoDto) {
        validateOnCreate(boletoDto);
        Boleto boleto = createEntityFromDto(boletoDto, false);
        return boletoRepository.persist(boleto);
    }

    private Boleto createEntityFromDto(BoletoDto boletoDto, Boolean edicao) {
        Boleto boleto = new Boleto();
        if (edicao) {
            boleto.setId(boletoDto.getId());
        }
        boleto.setDataHoraCadastro(boletoDto.getDataHoraCadastro());
        boleto.setArquivo(boletoDto.getArquivo());
        boleto.setSituacao(boletoDto.getSituacao());
        boleto.setUsuario(boletoDto.getUsuario());
        return boleto;
    }

    private void validateOnCreate(BoletoDto boletoDto) {
        if (boletoDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (boletoDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (boletoDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(boletoDto.getArquivo())) {
            throw new FieldCannotBeNullException("arquivo");
        }
        if (StringUtils.isBlank(boletoDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (boletoDto.getUsuario() == null) {
            throw new FieldCannotBeNullException("usuario");
        }
    }

    public void update(BoletoDto boletoDto) {
        validateOnUpdate(boletoDto);
        Boleto boleto = createEntityFromDto(boletoDto, true);
        boletoRepository.merge(boleto);
    }

    private void validateOnUpdate(BoletoDto boletoDto) {
        if (boletoDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (boletoDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        Boleto boleto = boletoRepository.findById(boletoDto.getId());
        if (boleto == null) {
            throw new ResourceNotFoundException("boleto", boletoDto.getId().toString());
        }
        if (boletoDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(boletoDto.getArquivo())) {
            throw new FieldCannotBeNullException("arquivo");
        }
        if (StringUtils.isBlank(boletoDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (boletoDto.getUsuario() == null) {
            throw new FieldCannotBeNullException("usuario");
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Boleto boleto = boletoRepository.findById(id);
        if (boleto == null) {
            throw new ResourceNotFoundException("boleto", id.toString());
        }
        boletoRepository.remove(boleto);
    }

}
