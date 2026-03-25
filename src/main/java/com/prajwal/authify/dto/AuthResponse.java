package com.prajwal.authify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String email;
    private String token;

}
