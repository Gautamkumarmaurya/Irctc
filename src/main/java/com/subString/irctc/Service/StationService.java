package com.subString.irctc.Service;


import com.subString.irctc.DTO.PagedResponse;
import com.subString.irctc.DTO.StationDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface StationService {

    StationDTO createStation(@Valid @RequestBody StationDTO stationDTO);

    PagedResponse<StationDTO> listStation(int page, int size, String sortBy, String sortDir);

    StationDTO getStationByCode(String trainNo);

    StationDTO UpdateStation(Long id, StationDTO stationDTO);

    void DeleteStation(Long id);
}

