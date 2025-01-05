package com.infosys.module4.service;

import com.infosys.module4.dto.FlatDto;
import com.infosys.module4.dto.ParkingDto;
import com.infosys.module4.exception.ParkingException;
import com.infosys.module4.feign.SocietyManagementInterface;
import com.infosys.module4.model.Parking;
import com.infosys.module4.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImplementation implements ParkingService{
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    SocietyManagementInterface societyManagementInterface;
    @Override
    public Parking addParking(String jwt,ParkingDto parkingDto) throws ParkingException{
        Parking parking=new Parking();
        FlatDto flat=societyManagementInterface.getFlatByFlatNoAndSocietyId(jwt,parkingDto.getFlatNo());
        parking.setParkingNo(parkingDto.getParkingNo());
        parking.setFlatNo(parkingDto.getFlatNo());
        parking.setFlatId(flat.getFlatId());
        parking.setSocietyId(flat.getSocietyId());
        Parking newParking=parkingRepository.save(parking);
        if (newParking.getParkingId()!=null){
            return newParking;
        }
        throw new ParkingException("Unable To Add");
    }

    @Override
    public List<Parking> getAllParking() {
        return parkingRepository.findAll();
    }
}
