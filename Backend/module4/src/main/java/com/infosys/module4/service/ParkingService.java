package com.infosys.module4.service;

import com.infosys.module4.dto.ParkingDto;
import com.infosys.module4.exception.ParkingException;
import com.infosys.module4.model.Parking;

import java.util.List;

public interface ParkingService {
    public Parking addParking(String jwt,ParkingDto parkingDto) throws ParkingException;
    public List<Parking> getAllParking();
}
