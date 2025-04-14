package lk.iit.guardianchain.guardianchain.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollIdentityResponse {
    private String name;
    private String secret;
    private String token;
} 