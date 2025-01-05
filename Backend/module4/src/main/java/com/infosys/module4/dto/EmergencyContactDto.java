package com.infosys.module4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactDto {
    private String personName;
    private String serviceType;
    private String phoneNo;
    private String block;
}
