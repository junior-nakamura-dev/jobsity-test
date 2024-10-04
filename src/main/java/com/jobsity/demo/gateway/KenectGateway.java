package com.jobsity.demo.gateway;

import com.jobsity.demo.configuration.FeignConfiguration;
import com.jobsity.demo.gateway.dto.KenectContact;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "kenect", url = "https://k-messages-api.herokuapp.com/api/v1/", configuration = FeignConfiguration.class)
public interface KenectGateway {

    @GetMapping(value = "/contacts")
    ResponseEntity<KenectContact> getAllContacts(@RequestHeader(value = "Authorization") String authorizationHeader, @RequestParam int page);

}
