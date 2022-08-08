package com.extrawest.simplechargingstationtertyshniy.model.dto.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AuthenticationRequestDTO {
    private String email;
    @Size(min = 4, max = 12)
    private String password;
}

