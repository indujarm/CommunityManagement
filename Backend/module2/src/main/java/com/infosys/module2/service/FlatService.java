package com.infosys.module2.service;

import com.infosys.module2.exception.RegistrationException;
import com.infosys.module2.model.Flat;

import java.util.List;

public interface FlatService {
    Flat addFlat(Flat flat);
    public Flat getFlatByFlatNoAndSocietyId(String flatNo, Long societyId) throws RegistrationException;
    List<Flat> getAllFlats();
    Flat updateFlatRent(Long id,Long rent);
}
