package com.etrawest.simplechargingstationtertyshniy.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    private long id;
    private String email;
    private String password;
    private long phoneNumber;
}
