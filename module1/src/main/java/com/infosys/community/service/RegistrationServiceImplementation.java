package com.infosys.community.service;

import com.infosys.community.model.AdminDetails;
import com.infosys.community.model.ResidentDetails;
import com.infosys.community.repository.AdminDetailsRepository;
import com.infosys.community.repository.ResidentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImplementation implements RegistationService{
    @Autowired
    AdminDetailsRepository adminDetailsRepository;
    @Autowired
    ResidentDetailsRepository residentDetailsRepository;

    @Override
    public void adminRegistration(AdminDetails adminDetails) {
        adminDetailsRepository.save(adminDetails);
    }

    @Override
    public void residentRegistration(ResidentDetails residentDetails) {
        residentDetailsRepository.save(residentDetails);
    }
}