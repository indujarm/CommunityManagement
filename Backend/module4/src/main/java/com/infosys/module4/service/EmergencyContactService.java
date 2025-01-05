package com.infosys.module4.service;

import com.infosys.module4.dto.EmergencyContactDto;
import com.infosys.module4.exception.EmergencyContactException;
import com.infosys.module4.model.EmergencyContact;
import com.infosys.module4.response.EmergencyContactResponse;

import java.util.List;

public interface EmergencyContactService {

    public EmergencyContactResponse addContact(EmergencyContactDto emergencyContactDto, String jwt) throws EmergencyContactException;
    public List<EmergencyContact> getAllContacts();
    public EmergencyContactResponse updateContact(EmergencyContactDto emergencyContactDto,Long emergencyContactId) throws EmergencyContactException;
    public EmergencyContactResponse deleteContact(Long emergencyContactId) throws EmergencyContactException;
}
