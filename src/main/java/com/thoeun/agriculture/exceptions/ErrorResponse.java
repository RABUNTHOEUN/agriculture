package com.thoeun.agriculture.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String details;

}
