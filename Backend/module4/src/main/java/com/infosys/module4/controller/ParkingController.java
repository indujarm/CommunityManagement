package com.infosys.module4.controller;

import com.infosys.module4.dto.ParkingDto;
import com.infosys.module4.model.Parking;
import com.infosys.module4.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @PostMapping("/addParking")
    public Parking addParking(@RequestHeader("Authorization") String jwt,@RequestBody ParkingDto parkingDto){
        return parkingService.addParking(jwt,parkingDto);
    }

    @GetMapping("/getAllParking")
    public List<Parking> getAllParking(@RequestHeader("Authorization") String jwt){
        return parkingService.getAllParking();
    }
}
