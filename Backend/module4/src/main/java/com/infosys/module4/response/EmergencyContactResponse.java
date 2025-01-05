package com.infosys.module4.response;

import com.infosys.module4.model.EmergencyContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmergencyContactResponse {
    private EmergencyContact emergencyContact;
    private String message;
}
