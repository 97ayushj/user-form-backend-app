package com.example.userformbackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Response {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;
}
