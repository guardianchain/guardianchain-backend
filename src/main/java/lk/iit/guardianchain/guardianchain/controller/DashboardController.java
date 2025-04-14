package lk.iit.guardianchain.guardianchain.controller;

import lk.iit.guardianchain.guardianchain.dto.DashboardStatsDTO;
import lk.iit.guardianchain.guardianchain.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats(
            @RequestParam(required = false) String fiId) {
        if (fiId != null && !fiId.isEmpty()) {
            return ResponseEntity.ok(dashboardService.getDashboardStatsByFiId(fiId));
        }
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }
} 