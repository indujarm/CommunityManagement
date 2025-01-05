package com.infosys.module3.service;

import com.infosys.module3.dto.RequestDto;
import com.infosys.module3.exception.RequestException;

public interface RequestService {
    public String sendRequest(RequestDto requestDto) throws RequestException;
}
