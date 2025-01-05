package com.infosys.module4.service;

import com.infosys.module4.dto.EmergencyContactDto;
import com.infosys.module4.dto.SocietyDto;
import com.infosys.module4.exception.EmergencyContactException;
import com.infosys.module4.feign.SocietyManagementInterface;
import com.infosys.module4.model.EmergencyContact;
import com.infosys.module4.repository.EmergencyContactRepository;
import com.infosys.module4.response.EmergencyContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmergencyContactServiceImplementation implements EmergencyContactService {

    @Autowired
    EmergencyContactRepository emergencyContactRepository;

    @Autowired
    SocietyManagementInterface societyManagementInterface;

    private static final String PHONE_NUMBER_REGEX = "^[6-9]\\d{9}$";

    @Override
    public EmergencyContactResponse addContact(EmergencyContactDto emergencyContactDto, String jwt) throws EmergencyContactException {
        // Validate phone number
        if (!emergencyContactDto.getPhoneNo().matches(PHONE_NUMBER_REGEX)) {
            throw new EmergencyContactException("Invalid Phone Number");
        }

        SocietyDto societyDto = societyManagementInterface.getAdminDetails(jwt);
        EmergencyContact contact = new EmergencyContact();
        contact.setPersonName(emergencyContactDto.getPersonName());
        contact.setBlock(emergencyContactDto.getBlock());
        contact.setPhoneNo(emergencyContactDto.getPhoneNo());
        contact.setServiceType(emergencyContactDto.getServiceType());
        contact.setSocietyId(societyDto.getSocietyId());

        EmergencyContact savedContact = emergencyContactRepository.save(contact);
        if (savedContact.getEmergencyId() != null) {
            return new EmergencyContactResponse(savedContact, "Contact Added Successfully");
        }
        throw new EmergencyContactException("Unable To Add Contact");
    }

    @Override
    public List<EmergencyContact> getAllContacts() {
        return emergencyContactRepository.findAll();
    }

    @Override
    public EmergencyContactResponse updateContact(EmergencyContactDto emergencyContactDto, Long emergencyId) throws EmergencyContactException {
        // Validate phone number
        if (!emergencyContactDto.getPhoneNo().matches(PHONE_NUMBER_REGEX)) {
            throw new EmergencyContactException("Invalid Phone Number: Must be a 10-digit number starting with 6-9.");
        }

        Optional<EmergencyContact> emergencyContact = emergencyContactRepository.findById(emergencyId);
        if (emergencyContact.isPresent()) {
            EmergencyContact existingContact = emergencyContact.get();

            if (!existingContact.getPersonName().equals(emergencyContactDto.getPersonName())) {
                existingContact.setPersonName(emergencyContactDto.getPersonName());
            }
            if (!existingContact.getServiceType().equals(emergencyContactDto.getServiceType())) {
                existingContact.setServiceType(emergencyContactDto.getServiceType());
            }
            if (!existingContact.getPhoneNo().equals(emergencyContactDto.getPhoneNo())) {
                existingContact.setPhoneNo(emergencyContactDto.getPhoneNo());
            }
            if (!existingContact.getBlock().equals(emergencyContactDto.getBlock())) {
                existingContact.setBlock(emergencyContactDto.getBlock());
            }
            EmergencyContact updatedContact = emergencyContactRepository.save(existingContact);
            return new EmergencyContactResponse(updatedContact, "Contact Updated Successfully");
        }
        throw new EmergencyContactException("Contact Not Found");
    }

    @Override
    public EmergencyContactResponse deleteContact(Long emergencyId) throws EmergencyContactException {
        if (!emergencyContactRepository.existsById(emergencyId)) {
            throw new EmergencyContactException("Contact Not Found");
        }
        emergencyContactRepository.deleteById(emergencyId);
        return new EmergencyContactResponse(null, "Contact Deleted Successfully");
    }
}
