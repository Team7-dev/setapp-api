package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.VeiculoDto;
import br.com.uniplan.pim.setappapi.entity.Veiculo;
import br.com.uniplan.pim.setappapi.exception.*;
import br.com.uniplan.pim.setappapi.repository.VeiculoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<VeiculoDto> findAll() {
        List<VeiculoDto> veiculosDto = new ArrayList<>();
        List<Veiculo> veiculos = veiculoRepository.findAll();
        for (Veiculo veiculo : veiculos) {
            veiculosDto.add(createDtoFromEntity(veiculo));
        }
        return veiculosDto;
    }

    public VeiculoDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Veiculo veiculo = veiculoRepository.findById(id);
        if (veiculo == null) {
            throw new ResourceNotFoundException("veiculo", id.toString());
        }
        VeiculoDto veiculoDto = createDtoFromEntity(veiculo);
        return veiculoDto;
    }

    private VeiculoDto createDtoFromEntity(Veiculo veiculo) {
        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setId(veiculo.getId());
        veiculoDto.setDataHoraCadastro(veiculo.getDataHoraCadastro());
        veiculoDto.setPlaca(veiculo.getPlaca());
        veiculoDto.setSituacao(veiculo.getSituacao());
        veiculoDto.setUnidade(veiculo.getUnidade());
        veiculoDto.setUsuario(veiculo.getUsuario());
        return veiculoDto;
    }

    public Long create(VeiculoDto veiculoDto) {
        validateOnCreate(veiculoDto);
        Veiculo veiculo = createEntityFromDto(veiculoDto, false);
        return veiculoRepository.persist(veiculo);
    }

    private Veiculo createEntityFromDto(VeiculoDto veiculoDto, Boolean edicao) {
        Veiculo veiculo = new Veiculo();
        if (edicao) {
            veiculo.setId(veiculoDto.getId());
        }
        veiculo.setDataHoraCadastro(veiculoDto.getDataHoraCadastro());
        veiculo.setPlaca(veiculoDto.getPlaca());
        veiculo.setSituacao(veiculoDto.getSituacao());
        veiculo.setUnidade(veiculoDto.getUnidade());
        veiculo.setUsuario(veiculoDto.getUsuario());
        return veiculo;
    }

    private void validateOnCreate(VeiculoDto veiculoDto) {
        if (veiculoDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (veiculoDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (veiculoDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(veiculoDto.getPlaca())) {
            throw new FieldCannotBeNullException("placa");
        }
        if (StringUtils.isBlank(veiculoDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        } else if ("OCUPADO".equals(veiculoDto.getSituacao())) {
            if (veiculoDto.getUsuario() == null) {
                throw new FieldCannotBeNullException("usuario");
            } else {
                long count = veiculoRepository.countByUsuario(veiculoDto.getUsuario().getId());
                if (count > 0) {
                    throw new UniqueFieldContraintException("usuario");
                }
            }
            if (veiculoDto.getUnidade() == null) {
                throw new FieldCannotBeNullException("unidade");
            } else {
                long count = veiculoRepository.countByUnidade(veiculoDto.getUnidade().getId());
                if (count > 0) {
                    throw new UniqueFieldContraintException("unidade");
                }
            }
        }
    }

    public void update(VeiculoDto veiculoDto) {
        validateOnUpdate(veiculoDto);
        Veiculo veiculo = createEntityFromDto(veiculoDto, true);
        veiculoRepository.merge(veiculo);
    }

    private void validateOnUpdate(VeiculoDto veiculoDto) {
        if (veiculoDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (veiculoDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        Veiculo veiculo = veiculoRepository.findById(veiculoDto.getId());
        if (veiculo == null) {
            throw new ResourceNotFoundException("veiculo", veiculoDto.getId().toString());
        }
        if (veiculoDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(veiculoDto.getPlaca())) {
            throw new FieldCannotBeNullException("placa");
        }
        if (StringUtils.isBlank(veiculoDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        } else if ("OCUPADO".equals(veiculoDto.getSituacao())) {
            if (veiculoDto.getUsuario() == null) {
                throw new FieldCannotBeNullException("usuario");
            } else {
                long count = veiculoRepository.countByUsuarioExceptWithId(veiculoDto.getUsuario().getId(), veiculoDto.getId());
                if (count > 0) {
                    throw new UniqueFieldContraintException("usuario");
                }
            }
            if (veiculoDto.getUnidade() == null) {
                throw new FieldCannotBeNullException("unidade");
            } else {
                long count = veiculoRepository.countByUnidadeExceptWithId(veiculoDto.getUnidade().getId(), veiculoDto.getId());
                if (count > 0) {
                    throw new UniqueFieldContraintException("unidade");
                }
            }
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Veiculo veiculo = veiculoRepository.findById(id);
        if (veiculo == null) {
            throw new ResourceNotFoundException("veiculo", id.toString());
        }
        veiculoRepository.remove(veiculo);
    }

}
