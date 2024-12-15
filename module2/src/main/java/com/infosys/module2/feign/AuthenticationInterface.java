package com.infosys.module2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="module1")
@Component
public interface AuthenticationInterface {
    @GetMapping("/get-email")
    public String getEmailFromJWT(@RequestHeader("Authorization") String jwt);

}