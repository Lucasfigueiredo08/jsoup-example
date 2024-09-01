package com.lucasliberato.jsoupproject.demo.controller;

import com.lucasliberato.jsoupproject.demo.domain.dtos.RespondentDTO;
import com.lucasliberato.jsoupproject.demo.domain.service.ScraperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/")
public class ScraperController {

    @Autowired
    ScraperServiceImpl scraperService;

    @GetMapping(path = "/{vehicleModel}")
    public Set<RespondentDTO> getVehicleByModel(@PathVariable String vehicleModel) {
        return  scraperService.getVehicleByModel(vehicleModel);
    }
}

