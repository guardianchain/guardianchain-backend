package lk.iit.guardianchain.guardianchain.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private UserDTO user;
} 