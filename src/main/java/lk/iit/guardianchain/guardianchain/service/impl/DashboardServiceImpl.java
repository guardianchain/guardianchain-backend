package lk.iit.guardianchain.guardianchain.service.impl;


import lk.iit.guardianchain.guardianchain.dto.ApiAbuseStatsDTO;
import lk.iit.guardianchain.guardianchain.dto.ApiContextStatsDTO;
import lk.iit.guardianchain.guardianchain.dto.DashboardStatsDTO;
import lk.iit.guardianchain.guardianchain.dto.FinancialInstitutionStatsDTO;
import lk.iit.guardianchain.guardianchain.model.ApiAbuse;
import lk.iit.guardianchain.guardianchain.repository.ApiAbuseRepository;
import lk.iit.guardianchain.guardianchain.repository.ApiUserRepository;
import lk.iit.guardianchain.guardianchain.repository.FinancialInstitutionRepository;
import lk.iit.guardianchain.guardianchain.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ApiAbuseRepository apiAbuseRepository;

    @Autowired
    private ApiUserRepository apiUserRepository;

    @Autowired
    private FinancialInstitutionRepository financialInstitutionRepository;

    @Override
    public DashboardStatsDTO getDashboardStats() {
        return getDashboardStatsByFiId(null);
    }

    @Override
    public DashboardStatsDTO getDashboardStatsByFiId(String fiId) {
        // Get current timestamp and last week's timestamp
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastWeek = now.minusWeeks(1);
        LocalDateTime twoWeeksAgo = now.minusWeeks(2);

        // Get all abuses from the last two weeks
        List<ApiAbuse> recentAbuses = apiAbuseRepository.findByReportedDateTimeAfter(twoWeeksAgo);
        
        // Filter by fiId if provided
        if (fiId != null && !fiId.isEmpty()) {
            recentAbuses = recentAbuses.stream()
                    .filter(abuse -> fiId.equals(abuse.getFiId()))
                    .collect(Collectors.toList());
        }

        // Split abuses by week
        List<ApiAbuse> thisWeekAbuses = recentAbuses.stream()
                .filter(abuse -> abuse.getReportedDateTime().isAfter(lastWeek))
                .collect(Collectors.toList());
        
        List<ApiAbuse> lastWeekAbuses = recentAbuses.stream()
                .filter(abuse -> abuse.getReportedDateTime().isBefore(lastWeek))
                .collect(Collectors.toList());

        // Calculate percentage change
        double percentageChange = calculatePercentageChange(lastWeekAbuses.size(), thisWeekAbuses.size());

        // Get abuse types count
        Map<String, Long> abuseTypeCounts = thisWeekAbuses.stream()
                .collect(Collectors.groupingBy(ApiAbuse::getType, Collectors.counting()));

        // Get most abused API context
        Map<String, Long> contextCounts = thisWeekAbuses.stream()
                .collect(Collectors.groupingBy(ApiAbuse::getApiContext, Collectors.counting()));
        Map.Entry<String, Long> mostAbusedContext = contextCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(new AbstractMap.SimpleEntry<>("None", 0L));

        // Get most targeted FI (only if fiId is not provided)
        Map.Entry<String, Long> mostTargetedFI;
        if (fiId != null && !fiId.isEmpty()) {
            mostTargetedFI = new AbstractMap.SimpleEntry<>(fiId, 
                (long) recentAbuses.stream().filter(abuse -> fiId.equals(abuse.getFiId())).count());
        } else {
            Map<String, Long> fiCounts = thisWeekAbuses.stream()
                    .collect(Collectors.groupingBy(ApiAbuse::getFiId, Collectors.counting()));
            mostTargetedFI = fiCounts.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .orElse(new AbstractMap.SimpleEntry<>("None", 0L));
        }

        // Get total counts based on fiId filter
        long totalApiAbuses = fiId != null && !fiId.isEmpty() 
            ? apiAbuseRepository.countByFiId(fiId)
            : apiAbuseRepository.count();

        long totalApiUsers = fiId != null && !fiId.isEmpty()
            ? apiAbuseRepository.findByFiId(fiId).stream()
                .map(ApiAbuse::getApiUser)
                .distinct()
                .count()
            : apiUserRepository.count();

        return DashboardStatsDTO.builder()
                .totalApiAbuses(totalApiAbuses)
                .totalFinancialInstitutions(fiId != null && !fiId.isEmpty() ? 1 : financialInstitutionRepository.count())
                .totalApiUsers(totalApiUsers)
                .abusePercentageChange(percentageChange)
                .weeklyAbuseStats(ApiAbuseStatsDTO.builder()
                        .totalAbuses(thisWeekAbuses.size())
                        .lastWeekAbuses(lastWeekAbuses.size())
                        .percentageChange(percentageChange)
                        .unauthorizedAccessCount(abuseTypeCounts.getOrDefault("UNAUTHORIZED_ACCESS", 0L))
                        .excessiveRequestsCount(abuseTypeCounts.getOrDefault("EXCESSIVE_REQUESTS", 0L))
                        .otherAbusesCount(abuseTypeCounts.getOrDefault("OTHER", 0L))
                        .build())
                .apiContextStats(ApiContextStatsDTO.builder()
                        .mostAbusedApiContext(mostAbusedContext.getKey())
                        .abuseCount(mostAbusedContext.getValue())
                        .percentageOfTotal(calculatePercentage(mostAbusedContext.getValue(), thisWeekAbuses.size()))
                        .build())
                .fiStats(FinancialInstitutionStatsDTO.builder()
                        .mostTargetedFI(mostTargetedFI.getKey())
                        .abuseCount(mostTargetedFI.getValue())
                        .percentageOfTotal(calculatePercentage(mostTargetedFI.getValue(), thisWeekAbuses.size()))
                        .build())
                .build();
    }

    private double calculatePercentageChange(long oldValue, long newValue) {
        if (oldValue == 0) return newValue == 0 ? 0 : 100;
        return ((double) (newValue - oldValue) / oldValue) * 100;
    }

    private double calculatePercentage(long value, long total) {
        if (total == 0) return 0;
        return ((double) value / total) * 100;
    }
} 