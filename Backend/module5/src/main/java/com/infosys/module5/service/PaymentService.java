package com.infosys.module5.service;

import com.infosys.module5.model.Payment;
import com.razorpay.RazorpayException;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    public List<Payment> getAllPayments();
    public Payment createPayment(String jwt) throws RazorpayException;
    public void updateStatus(Map<String,String> response);
}
