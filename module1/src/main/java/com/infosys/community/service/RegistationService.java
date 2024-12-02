package com.infosys.community.service;

import com.infosys.community.model.AdminDetails;
import com.infosys.community.model.ResidentDetails;

public interface RegistationService {
     public void adminRegistration(AdminDetails adminDetails);
     public void residentRegistration(ResidentDetails residentDetails);
}