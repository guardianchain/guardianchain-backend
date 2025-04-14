package lk.iit.guardianchain.guardianchain.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class DashboardStatsDTO {
    private long totalApiAbuses;
    private long totalFinancialInstitutions;
    private long totalApiUsers;
    private double abusePercentageChange;
    private ApiAbuseStatsDTO weeklyAbuseStats;
    private ApiContextStatsDTO apiContextStats;
    private FinancialInstitutionStatsDTO fiStats;
}
//
//@Data
//@Builder
//class ApiAbuseStatsDTO {
//    private long totalAbuses;
//    private long lastWeekAbuses;
//    private double percentageChange;
//    private long unauthorizedAccessCount;
//    private long excessiveRequestsCount;
//    private long otherAbusesCount;
//}
//
//@Data
//@Builder
//class ApiContextStatsDTO {
//    private String mostAbusedApiContext;
//    private long abuseCount;
//    private double percentageOfTotal;
//}
//
//@Data
//@Builder
//class FinancialInstitutionStatsDTO {
//    private String mostTargetedFI;
//    private long abuseCount;
//    private double percentageOfTotal;
//}