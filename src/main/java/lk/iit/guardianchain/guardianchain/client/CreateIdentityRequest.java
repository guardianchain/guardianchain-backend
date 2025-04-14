package lk.iit.guardianchain.guardianchain.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateIdentityRequest {
    private String name;
    private String type;
    private Integer maxEnrollments;
    private Object attributes;
} 