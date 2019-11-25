package br.com.uniplan.pim.setappapi.controller;

import br.com.uniplan.pim.setappapi.dto.BoletoDto;
import br.com.uniplan.pim.setappapi.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boleto")
public class BoletoController {

    private BoletoService boletoService;

    @Autowired
    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public List<BoletoDto> findAll() {
        return boletoService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public BoletoDto findById(@PathVariable("id") Long id) {
        return boletoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Long create(@RequestBody BoletoDto boletoDto) {
        return boletoService.create(boletoDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody BoletoDto boletoDto) {
        boletoService.update(boletoDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        boletoService.deleteById(id);
    }

}
