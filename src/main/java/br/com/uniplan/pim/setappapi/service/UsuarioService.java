package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.UsuarioDto;
import br.com.uniplan.pim.setappapi.entity.Usuario;
import br.com.uniplan.pim.setappapi.exception.*;
import br.com.uniplan.pim.setappapi.repository.PerfilRepository;
import br.com.uniplan.pim.setappapi.repository.UsuarioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private PerfilRepository perfilRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDto> findAll() {
        List<UsuarioDto> usuariosDto = new ArrayList<>();
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            usuariosDto.add(createDtoFromEntity(usuario));
        }
        return usuariosDto;
    }

    public UsuarioDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new ResourceNotFoundException("usuario", id.toString());
        }
        UsuarioDto usuarioDto = createDtoFromEntity(usuario);
        return usuarioDto;
    }

    private UsuarioDto createDtoFromEntity(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setDataHoraCadastro(usuario.getDataHoraCadastro());
        usuarioDto.setUsuario(usuario.getUsuario());
        usuarioDto.setSenha(usuario.getSenha());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setCpf(usuario.getCpf());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setSituacao(usuario.getSituacao());
        usuarioDto.setPerfil(usuario.getPerfil());
        return usuarioDto;
    }

    public Long create(UsuarioDto usuarioDto) {
        validateOnCreate(usuarioDto);
        Usuario usuario = createEntityFromDto(usuarioDto, false);
        return usuarioRepository.persist(usuario);
    }

    private Usuario createEntityFromDto(UsuarioDto usuarioDto, Boolean edicao) {
        Usuario usuario = new Usuario();
        if (edicao) {
            usuario.setId(usuarioDto.getId());
        }
        usuario.setDataHoraCadastro(usuarioDto.getDataHoraCadastro());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setNome(usuarioDto.getNome());
        usuario.setCpf(usuarioDto.getCpf());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSituacao(usuarioDto.getSituacao());
        usuario.setPerfil(usuarioDto.getPerfil());
        return usuario;
    }

    private void validateOnCreate(UsuarioDto usuarioDto) {
        if (usuarioDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (usuarioDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (usuarioDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(usuarioDto.getUsuario())) {
            throw new FieldCannotBeNullException("usuario");
        } else {
            Long count = usuarioRepository.countByUsername(usuarioDto.getUsuario());
            if (count > 0) {
                throw new UniqueFieldContraintException("usuario");
            }
        }
        if (StringUtils.isBlank(usuarioDto.getSenha())) {
            throw new FieldCannotBeNullException("senha");
        }
        if (StringUtils.isBlank(usuarioDto.getNome())) {
            throw new FieldCannotBeNullException("nome");
        } else {
            Long count = usuarioRepository.countByName(usuarioDto.getNome());
            if (count > 0) {
                throw new UniqueFieldContraintException("nome");
            }
        }
        if (StringUtils.isBlank(usuarioDto.getCpf())) {
            throw new FieldCannotBeNullException("cpf");
        } else {
            Long count = usuarioRepository.countByCpf(usuarioDto.getCpf());
            if (count > 0) {
                throw new UniqueFieldContraintException("cpf");
            }
        }
        if (StringUtils.isBlank(usuarioDto.getEmail())) {
            throw new FieldCannotBeNullException("email");
        }
        if (StringUtils.isBlank(usuarioDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (usuarioDto.getPerfil() == null) {
            throw new FieldCannotBeNullException("perfil");
        }
    }

    public void update(UsuarioDto usuarioDto) {
        validateOnUpdate(usuarioDto);
        Usuario usuario = createEntityFromDto(usuarioDto, true);
        usuarioRepository.merge(usuario);
    }

    private void validateOnUpdate(UsuarioDto usuarioDto) {
        if (usuarioDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (usuarioDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        Usuario usuario = usuarioRepository.findById(usuarioDto.getId());
        if (usuario == null) {
            throw new ResourceNotFoundException("usuario", usuarioDto.getId().toString());
        }
        if (usuarioDto.getDataHoraCadastro() == null) {
            throw new FieldCannotBeNullException("dataHoraCadastro");
        }
        if (StringUtils.isBlank(usuarioDto.getUsuario())) {
            throw new FieldCannotBeNullException("usuario");
        } else {
            Long count = usuarioRepository.countByUsernameExceptWithId(usuarioDto.getUsuario(), usuarioDto.getId());
            if (count > 0) {
                throw new UniqueFieldContraintException("usuario");
            }
        }
        if (StringUtils.isBlank(usuarioDto.getSenha())) {
            throw new FieldCannotBeNullException("senha");
        }
        if (StringUtils.isBlank(usuarioDto.getNome())) {
            throw new FieldCannotBeNullException("nome");
        }
        if (StringUtils.isBlank(usuarioDto.getCpf())) {
            throw new FieldCannotBeNullException("cpf");
        } else {
            Long count = usuarioRepository.countByCpfExceptWithId(usuarioDto.getCpf(), usuarioDto.getId());
            if (count > 0) {
                throw new UniqueFieldContraintException("cpf");
            }
        }
        if (StringUtils.isBlank(usuarioDto.getEmail())) {
            throw new FieldCannotBeNullException("email");
        }
        if (StringUtils.isBlank(usuarioDto.getSituacao())) {
            throw new FieldCannotBeNullException("situacao");
        }
        if (usuarioDto.getPerfil() == null) {
            throw new FieldCannotBeNullException("perfil");
        } else {
            usuarioDto.setPerfil(perfilRepository.findByPerfil(usuarioDto.getPerfil().getPerfil()));
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new ResourceNotFoundException("usuario", id.toString());
        }
        usuarioRepository.remove(usuario);
    }

    public List<UsuarioDto> findActives() {
        List<UsuarioDto> usuariosDto = new ArrayList<>();
        List<Usuario> usuarios = usuarioRepository.findActives();
        for (Usuario usuario : usuarios) {
            usuariosDto.add(createDtoFromEntity(usuario));
        }
        return usuariosDto;
    }
}
