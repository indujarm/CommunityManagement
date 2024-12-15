package com.infosys.module2.service;

import com.infosys.module2.exception.RegistrationException;
import com.infosys.module2.model.Flat;

import java.util.List;

public interface FlatService {
    Flat addFlat(Flat flat);
    Flat getFlatByFlatNo(String flatNo) throws RegistrationException;
    List<Flat> getAllFlats();
}
