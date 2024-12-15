package com.infosys.module2.controller;

import com.infosys.module2.dto.FlatDto;
import com.infosys.module2.model.Flat;
import com.infosys.module2.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlatController {

    @Autowired
    FlatService flatService;
    @PostMapping("/addFlat")
    public Flat addFlat(@RequestBody FlatDto flatDto){
        Flat flat=new Flat();
        flat.setFlatNo(flatDto.getFlatNo());
        flat.setSocietyId(flatDto.getSocietyId());
        flat.setOccupied(false);
        System.out.println("Flat values"+flat);
        return flatService.addFlat(flat);
    }

    @GetMapping("/getAllFlats")
    public List<Flat> getAllFlats(){
        return flatService.getAllFlats();
    }
}
