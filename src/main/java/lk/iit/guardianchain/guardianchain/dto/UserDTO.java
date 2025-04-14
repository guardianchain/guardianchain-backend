package lk.iit.guardianchain.guardianchain.dto;

import lombok.Data;
import lk.iit.guardianchain.guardianchain.enums.UserRole;

@Data
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private UserRole role;
    private Long financialInstitutionId;
} 