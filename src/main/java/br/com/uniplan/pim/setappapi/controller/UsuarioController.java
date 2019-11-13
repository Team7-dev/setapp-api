package br.com.uniplan.pim.setappapi.controller;

import br.com.uniplan.pim.setappapi.dto.UsuarioDto;
import br.com.uniplan.pim.setappapi.service.UsuarioService;
import br.com.uniplan.pim.setappapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public List<UsuarioDto> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public UsuarioDto findById(@PathVariable("id") Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Long create(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.create(usuarioDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody UsuarioDto usuarioDto) {
        usuarioService.update(usuarioDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        usuarioService.deleteById(id);
    }

}
