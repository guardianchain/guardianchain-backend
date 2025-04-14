package lk.iit.guardianchain.guardianchain.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollIdentityRequest {
    private String secret;
    private Object attributes;
} 