package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    @GetMapping
    public ResponseEntity<ApiResponse> hello(){
        return ResponseEntity.ok(new ApiResponse("Hello Welcome Agriculture api", null));
    }
}
