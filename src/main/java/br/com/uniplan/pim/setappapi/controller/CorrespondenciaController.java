package br.com.uniplan.pim.setappapi.controller;

import br.com.uniplan.pim.setappapi.dto.CorrespondenciaDto;
import br.com.uniplan.pim.setappapi.service.CorrespondenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/correspondencia")
public class CorrespondenciaController {

    private CorrespondenciaService correspondenciaService;

    @Autowired
    public CorrespondenciaController(CorrespondenciaService correspondenciaService) {
        this.correspondenciaService = correspondenciaService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public List<CorrespondenciaDto> findAll() {
        return correspondenciaService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public CorrespondenciaDto findById(@PathVariable("id") Long id) {
        return correspondenciaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Long create(@RequestBody CorrespondenciaDto correspondenciaDto) {
        return correspondenciaService.create(correspondenciaDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody CorrespondenciaDto correspondenciaDto) {
        correspondenciaService.update(correspondenciaDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        correspondenciaService.deleteById(id);
    }

    @GetMapping(value = "/pendentes")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CorrespondenciaDto> findPendant() {
        return correspondenciaService.findPendant();
    }

    @GetMapping(value = "/retiradas")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CorrespondenciaDto> findRemoval() {
        return correspondenciaService.findRemoval();
    }

}
