package br.com.uniplan.pim.setappapi.controller;

import br.com.uniplan.pim.setappapi.dto.AssembleiaDto;
import br.com.uniplan.pim.setappapi.service.AssembleiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assembleia")
public class AssembleiaController {

    private AssembleiaService assembleiaService;

    @Autowired
    public AssembleiaController(AssembleiaService assembleiaService) {
        this.assembleiaService = assembleiaService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public List<AssembleiaDto> findAll() {
        return assembleiaService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public AssembleiaDto findById(@PathVariable("id") Long id) {
        return assembleiaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Long create(@RequestBody AssembleiaDto assembleiaDto) {
        return assembleiaService.create(assembleiaDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody AssembleiaDto assembleiaDto) {
        assembleiaService.update(assembleiaDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        assembleiaService.deleteById(id);
    }

    @GetMapping(value = "/pendentes")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AssembleiaDto> findPendant() {
        return assembleiaService.findPendant();
    }

    @GetMapping(value = "/concluidas")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AssembleiaDto> findDone() {
        return assembleiaService.findDone();
    }

}
