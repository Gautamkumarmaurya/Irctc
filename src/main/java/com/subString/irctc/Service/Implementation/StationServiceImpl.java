package com.subString.irctc.Service.Implementation;

import com.subString.irctc.DTO.PagedResponse;
import com.subString.irctc.DTO.StationDTO;
import com.subString.irctc.Entity.Station;
import com.subString.irctc.Exception.ResponseNotFoundException;
import com.subString.irctc.Repository.StationRepository;
import com.subString.irctc.Service.StationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    private final ModelMapper modelMapper; // Model Mapper it means to convert the Entity to DtO and DTO to Entity

    //Constructor Dependency Injection
    public StationServiceImpl(StationRepository stationRepository, ModelMapper modelMapper) {
        this.stationRepository = stationRepository;
        this.modelMapper = modelMapper;
    }

    // Add Train
    @Override
    public StationDTO createStation(StationDTO stationDTO) {
         Station station = modelMapper.map(stationDTO, Station.class);
        Station savedStation = stationRepository.save(station);
        return modelMapper.map(savedStation, StationDTO.class);
    }

    // Get List Of Train
    @Override
    public PagedResponse<StationDTO> listStation(int page, int size, String sortBy, String sortDir) {
        // Pagination implementation
        Sort sort = sortDir.trim().equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Station> stations = stationRepository.findAll(pageable);
        Page<StationDTO> stationDTOS = stations.map(station -> modelMapper.map(station, StationDTO.class));
        return PagedResponse.fromPage(stationDTOS);
    }

    // get Single Train By TrainCode
    @Override
    public StationDTO getStationByCode(String code) {
        Station trainId = stationRepository.findByCode(code).orElseThrow(() -> new ResponseNotFoundException("Train Id Not found"));
        return modelMapper.map(trainId , StationDTO.class);
    }

    // Update Station
    @Override
    public StationDTO UpdateStation(Long id, StationDTO stationDTO) {
        Station station = stationRepository.findById(id).orElseThrow(() -> new ResponseNotFoundException("Train Id Not found"));
        // Update Details
        station.setCode(stationDTO.getCode());
        station.setName(stationDTO.getName());
        station.setCity(stationDTO.getCity());
        station.setState(stationDTO.getState());
        Station savedStation = stationRepository.save(station);
        return modelMapper.map(savedStation , StationDTO.class);
    }

    @Override
    public void DeleteStation(Long id) {
        Station station = stationRepository.findById(id).orElseThrow(() -> new ResponseNotFoundException("Train Id Not found"));
        stationRepository.delete(station);
        System.out.println("Deleted Station Id "+station.getId());
    }
}
