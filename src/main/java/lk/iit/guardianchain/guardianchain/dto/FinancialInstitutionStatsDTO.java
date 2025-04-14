package lk.iit.guardianchain.guardianchain.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class FinancialInstitutionStatsDTO {
    private String mostTargetedFI;
    private long abuseCount;
    private double percentageOfTotal;
} 