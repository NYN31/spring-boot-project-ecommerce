package com.ecommerce.ecommercebe.controller;

import com.ecommerce.ecommercebe.pojo.request.UserProfileEditRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.service.CommonUserFeaturesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CommonUserFeaturesController {
    @Autowired
    private CommonUserFeaturesService commonUserFeaturesService;

    @PostMapping(
            value = "/edit-profile",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CommonResponse editUserFeature(
            @RequestBody UserProfileEditRequest request,
            @RequestHeader(value = "token") String token){
        log.info("Request body: {}", request);
        return commonUserFeaturesService.editUserProfile(request, token);
    }
}
