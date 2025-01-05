package com.infosys.module2.service;

import com.infosys.module2.dto.ResidentDto;
import com.infosys.module2.dto.ResidentProfileDto;
import com.infosys.module2.exception.RegistrationException;
import com.infosys.module2.model.Resident;

import java.util.List;

public interface ResidentService {
    public String residentRegistration(ResidentDto residentDto,String jwt) throws RegistrationException;
    public List<Resident> getResidents();
    public Resident getResident(String jwt);
    public Resident getResidentProfile(String jwt);
}
