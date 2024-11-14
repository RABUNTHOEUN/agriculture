package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
    public ResponseEntity<ApiResponse> hello(){
        return ResponseEntity.ok(new ApiResponse("Hello Wellcome Agriculture api", null));
    }
}
