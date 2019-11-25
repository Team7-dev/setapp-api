package br.com.uniplan.pim.setappapi.controller;

import br.com.uniplan.pim.setappapi.dto.OcorrenciaDto;
import br.com.uniplan.pim.setappapi.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

    private OcorrenciaService ocorrenciaService;

    @Autowired
    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public List<OcorrenciaDto> findAll() {
        return ocorrenciaService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public OcorrenciaDto findById(@PathVariable("id") Long id) {
        return ocorrenciaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Long create(@RequestBody OcorrenciaDto ocorrenciaDto) {
        return ocorrenciaService.create(ocorrenciaDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody OcorrenciaDto ocorrenciaDto) {
        ocorrenciaService.update(ocorrenciaDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        ocorrenciaService.deleteById(id);
    }

}
