package com.infosys.module3.service;

import com.infosys.module3.dto.VendorDto;
import com.infosys.module3.model.Vendor;

import java.util.List;

public interface VendorService {
    public Vendor addVendor(String jwt,VendorDto vendorDto);
    public List<Vendor> getAllVendors();
    public Vendor updateVendor(Long vendorId,VendorDto vendorDto);
    public String deleteVendor(Long vendorId);
}
