package com.infosys.module2.service;

import com.infosys.module2.config.JwtProvider;
import com.infosys.module2.dto.ResidentDto;
import com.infosys.module2.dto.ResidentProfileDto;
import com.infosys.module2.exception.RegistrationException;
import com.infosys.module2.feign.AuthenticationInterface;
import com.infosys.module2.model.Flat;
import com.infosys.module2.model.Resident;
import com.infosys.module2.model.Society;
import com.infosys.module2.repository.FlatRepository;
import com.infosys.module2.repository.ResidentRepository;
import com.infosys.module2.repository.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ResidentServiceImplementation implements ResidentService{
    @Autowired
    ResidentRepository residentRepository;
    @Autowired
    AuthenticationInterface authenticationInterface;
    @Autowired
    FlatService flatService;
    @Autowired
    SocietyService societyService;
    @Autowired
    SocietyRepository societyRepository;
    @Autowired
    FlatRepository flatRepository;
    @Override
    public String residentRegistration(ResidentDto residentDto, String jwt) throws RegistrationException {
        // Fetch the society first to get the societyId
        Society society = societyService.getSocietyByName(residentDto.getSocietyName());
        if (society == null) {
            throw new RegistrationException("Invalid Society Name");
        }

        // Fetch the flat by flatNo and societyId
        Flat flat = flatService.getFlatByFlatNoAndSocietyId(residentDto.getFlatNo(), society.getSocietyId());
        if (flat == null) {
            throw new RegistrationException("Invalid Flat Number for the provided Society");
        }

        // Extract email from JWT
        String email = authenticationInterface.getEmailFromJWT(jwt);

        // Validate phone number
        String number = residentDto.getPhoneNo();
        String regex = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(number).matches()) {
            throw new RegistrationException("Phone Number is Invalid");
        }

        // Validate postal code
        if (!society.getPostal().equals(residentDto.getPostal())) {
            throw new RegistrationException("Postal is Invalid");
        }

        // Create a new Resident
        Resident newResident = new Resident();
        newResident.setName(residentDto.getName());
        newResident.setPhoneNo(residentDto.getPhoneNo());
        newResident.setFlatNo(residentDto.getFlatNo());
        newResident.setPostal(residentDto.getPostal());
        newResident.setEmail(email);
        newResident.setSocietyId(society.getSocietyId());
        newResident.setFlatId(flat.getFlatId());
        newResident.setRole("Resident");

        // Save the resident and update flat as occupied
        Resident savedResident = residentRepository.save(newResident);
        flat.setOccupied(true);
        flatRepository.save(flat);

        if (savedResident.getResidentId() == null) {
            throw new RegistrationException("Unable to Register");
        }

        return "Registration Successful";
    }


    @Override
    public List<Resident> getResidents() {
        return residentRepository.findAll();
    }

    @Override
    public Resident getResident(String jwt) {
        String email= JwtProvider.getEmailFromJwtToken(jwt);
        return residentRepository.findByEmail(email);
    }

    @Override
    public Resident getResidentProfile(String jwt) {
        Resident resident=getResident(jwt);
        return resident;
    }
}
