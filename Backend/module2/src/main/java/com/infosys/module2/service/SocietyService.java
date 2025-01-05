package com.infosys.module2.service;

import com.infosys.module2.dto.AdminDto;
import com.infosys.module2.dto.ResidentDto;
import com.infosys.module2.exception.RegistrationException;
import com.infosys.module2.model.Society;

import java.util.List;
import java.util.Optional;

public interface SocietyService {
    public String societyRegistration(AdminDto adminDto, String jwt) throws RegistrationException;
    public List<Society> getAllSocieties();
    public Society getSocietyByName(String name) throws RegistrationException;
    public Society getSocietyById(Long id);
    public Society getAdminDetails(String jwt);
}
