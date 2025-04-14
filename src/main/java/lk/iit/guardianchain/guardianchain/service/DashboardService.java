package lk.iit.guardianchain.guardianchain.service;

import lk.iit.guardianchain.guardianchain.dto.DashboardStatsDTO;

public interface DashboardService {
    DashboardStatsDTO getDashboardStats();
    DashboardStatsDTO getDashboardStatsByFiId(String fiId);
} 