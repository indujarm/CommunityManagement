package com.infosys.module4.feign;

import com.infosys.module4.dto.FlatDto;
import com.infosys.module4.dto.SocietyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="societymanagementservice")
@Component
public interface SocietyManagementInterface {
    @GetMapping("/api/get-admin")
    public SocietyDto getAdminDetails(@RequestHeader("Authorization") String jwt);
    @GetMapping("/api/flatByFlatNo")
    public FlatDto getFlatByFlatNoAndSocietyId(@RequestHeader("Authorization") String jwt,@RequestParam("flatNo") String flatNo);
}
