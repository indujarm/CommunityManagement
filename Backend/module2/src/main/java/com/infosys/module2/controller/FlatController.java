package com.infosys.module2.controller;

import com.infosys.module2.config.JwtProvider;
import com.infosys.module2.dto.FlatDto;
import com.infosys.module2.exception.RegistrationException;
import com.infosys.module2.model.Flat;
import com.infosys.module2.model.Society;
import com.infosys.module2.repository.SocietyRepository;
import com.infosys.module2.service.FlatService;
import com.infosys.module2.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlatController {

    @Autowired
    SocietyService societyService;
    @Autowired
    SocietyRepository societyRepository;
    @Autowired
    FlatService flatService;
    @PostMapping("/addFlat")
    public Flat addFlat(@RequestHeader("Authorization") String jwt,@RequestBody FlatDto flatDto){
        String email= JwtProvider.getEmailFromJwtToken(jwt);
        Society society=societyRepository.findByEmail(email);
        Flat flat=new Flat();
        flat.setFlatNo(flatDto.getFlatNo());
        flat.setOccupied(false);
        flat.setSocietyId(society.getSocietyId());
        return flatService.addFlat(flat);
    }
    @GetMapping("/getAllFlats")
    public List<Flat> getAllFlats(){
        return flatService.getAllFlats();
    }

    @GetMapping("/flatByFlatNo")
    public Flat getFlatByFlatNoAndSocietyId(@RequestHeader("Authorization") String jwt,@RequestParam String flatNo) throws RegistrationException {
        Society society=societyService.getAdminDetails(jwt);
        return flatService.getFlatByFlatNoAndSocietyId(flatNo,society.getSocietyId());
    }
}
