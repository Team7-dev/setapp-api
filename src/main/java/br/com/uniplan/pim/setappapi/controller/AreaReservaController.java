package br.com.uniplan.pim.setappapi.controller;

import br.com.uniplan.pim.setappapi.dto.AreaReservaDto;
import br.com.uniplan.pim.setappapi.service.AreaReservaService;
import br.com.uniplan.pim.setappapi.service.AreaReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areaReserva")
public class AreaReservaController {

    private AreaReservaService areaReservaService;

    @Autowired
    public AreaReservaController(AreaReservaService areaReservaService) {
        this.areaReservaService = areaReservaService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public List<AreaReservaDto> findAll() {
        return areaReservaService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('ADMIN') or hasRole('PUBLIC')")
    public AreaReservaDto findById(@PathVariable("id") Long id) {
        return areaReservaService.findById(id);
    }

}
