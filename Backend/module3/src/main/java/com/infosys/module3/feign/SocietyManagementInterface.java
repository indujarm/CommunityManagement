package com.infosys.module3.feign;

import com.infosys.module3.dto.ResidentDto;
import com.infosys.module3.dto.SocietyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "societymanagementservice")
public interface SocietyManagementInterface {
    @GetMapping("/api/getResidentByJWT")
    ResidentDto getResidentByJWT(@RequestHeader("Authorization") String jwt);
    @GetMapping("/api/get-admin")
    SocietyDto getAdminDetails(@RequestHeader("Authorization") String jwt);
}


