package com.infosys.module2.service;

import com.infosys.module2.exception.RegistrationException;
import com.infosys.module2.model.Flat;
import com.infosys.module2.repository.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlatServiceImplementation implements FlatService{
    @Autowired
    FlatRepository flatRepository;

    @Override
    public Flat addFlat(Flat flat) {
        return flatRepository.save(flat);
    }

    @Override
    public Flat getFlatByFlatNo(String flatNo) throws RegistrationException {
        Flat flat=flatRepository.findByFlatNo(flatNo);
        if (flat!=null){
            return flat;
        }
        throw new RegistrationException("Flat Not Found");
    }

    @Override
    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }
}
