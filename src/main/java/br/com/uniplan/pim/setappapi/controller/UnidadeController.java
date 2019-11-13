package br.com.uniplan.pim.setappapi.controller;

import br.com.uniplan.pim.setappapi.dto.UnidadeDto;
import br.com.uniplan.pim.setappapi.service.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    private UnidadeService unidadeService;

    @Autowired
    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public List<UnidadeDto> findAll() {
        return unidadeService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public UnidadeDto findById(@PathVariable("id") Long id) {
        return unidadeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Long create(@RequestBody UnidadeDto unidadeDto) {
        return unidadeService.create(unidadeDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody UnidadeDto unidadeDto) {
        unidadeService.update(unidadeDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        unidadeService.deleteById(id);
    }

}
