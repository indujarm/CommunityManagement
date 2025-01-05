package com.infosys.module3.controller;

import com.infosys.module3.dto.RequestDto;
import com.infosys.module3.exception.RequestException;
import com.infosys.module3.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class RequestController {

    @Autowired
    RequestService requestService;
    @PostMapping("/sendRequest")
    public String sendRequest(@RequestHeader("Authorization") String jwt,@RequestBody RequestDto requestDto) throws RequestException {
        return requestService.sendRequest(requestDto);
    }
}
