package com.infosys.module2.controller;

import com.infosys.module2.dto.ResidentDto;
import com.infosys.module2.exception.RegistrationException;
import com.infosys.module2.model.Resident;
import com.infosys.module2.service.FlatService;
import com.infosys.module2.service.ResidentService;
import com.infosys.module2.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResidentController {
    @Autowired
    ResidentService residentService;
    @Autowired
    FlatService flatService;
    @Autowired
    SocietyService societyService;
    @PostMapping("/resident-register")
    public String residentRegistration(@RequestHeader("Authorization") String jwt, @RequestBody ResidentDto residentDto) throws RegistrationException {
        System.out.println("Received JWT: " + jwt);
        System.out.println("Resident Details DTO: " + residentDto);
        return residentService.residentRegistration(residentDto,jwt);
    }
    @GetMapping("/residents")
    public List<Resident> getResidents(@RequestHeader("Authorization") String jwt){
        return residentService.getResidents();
    }

    @GetMapping("/getResidentProfile")
    public Resident getResidentProfile(@RequestHeader("Authorization") String jwt){
        return residentService.getResidentProfile(jwt);
    }

    @GetMapping("/getResidentByJWT")
    public Resident getResidentByJWT(@RequestHeader("Authorization") String jwt){
        return residentService.getResident(jwt);
    }
}
