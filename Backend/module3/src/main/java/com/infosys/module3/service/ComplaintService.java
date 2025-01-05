package com.infosys.module3.service;

import com.infosys.module3.dto.ComplaintDto;
import com.infosys.module3.exception.ComplaintException;
import com.infosys.module3.model.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint addComplaint(String jwt, ComplaintDto complaintDto) throws ComplaintException;
    List<Complaint> getAllComplaints();
    Complaint closeComplaint(Long complaintId);
}
