package lk.iit.guardianchain.guardianchain.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class ApiContextStatsDTO {
    private String mostAbusedApiContext;
    private long abuseCount;
    private double percentageOfTotal;
} 