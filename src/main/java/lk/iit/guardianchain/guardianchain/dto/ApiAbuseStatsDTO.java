package lk.iit.guardianchain.guardianchain.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class ApiAbuseStatsDTO {
    private long totalAbuses;
    private long lastWeekAbuses;
    private double percentageChange;
    private long unauthorizedAccessCount;
    private long excessiveRequestsCount;
    private long otherAbusesCount;
} 