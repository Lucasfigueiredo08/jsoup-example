package com.lucasliberato.jsoupproject.demo.domain.service;

import com.lucasliberato.jsoupproject.demo.domain.dtos.RespondentDTO;

import java.util.Set;

public interface ScraperService {

    Set<RespondentDTO> getVehicleByModel(String vehicleModel);
}
