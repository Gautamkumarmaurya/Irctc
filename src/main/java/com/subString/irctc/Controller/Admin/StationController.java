package com.subString.irctc.Controller;

import com.subString.irctc.Config.AppConstant;
import com.subString.irctc.DTO.PagedResponse;
import com.subString.irctc.DTO.StationDTO;
import com.subString.irctc.Service.StationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/stations")
public class StationController {

    private final StationService stationService;
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    // Add Station
    @PostMapping
    public ResponseEntity<StationDTO> createStation(@Valid @RequestBody StationDTO stationDTO){
        System.out.println("Create station method called");
        return new ResponseEntity<>(stationService.createStation(stationDTO), HttpStatus.OK);
    }

    // Get all Station
    @GetMapping
    public PagedResponse<StationDTO> listStations(
            @RequestParam(value = "page" , defaultValue = AppConstant.DEFAULT_PAGE) int page,
            @RequestParam(value = "size" , defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "sortBy" ,defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir" , defaultValue = "asc") String sortDir

            ){
        PagedResponse<StationDTO> stationDto = stationService.listStation(
                page,
                size,
                sortBy,
                sortDir
        );
        return stationDto;

    }

    // Get single Station
    @GetMapping("/{code}")
    public ResponseEntity<StationDTO> getStationByCode(@PathVariable("code") String code){
        return new ResponseEntity<>(stationService.getStationByCode(code) , HttpStatus.OK);
    }

    // Update Station By ID
    @PutMapping("/{id}")
    public ResponseEntity<StationDTO> UpdateStation(@PathVariable Long id ,@RequestBody StationDTO stationDTO){
       StationDTO stationDTO1 =  stationService.UpdateStation(id,stationDTO);
       return new ResponseEntity<>(stationDTO1, HttpStatus.OK);
    }

    // Station Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable("id") Long id){
        stationService.DeleteStation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
