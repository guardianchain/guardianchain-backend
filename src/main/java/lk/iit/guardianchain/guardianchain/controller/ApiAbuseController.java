package lk.iit.guardianchain.guardianchain.controller;

import lk.iit.guardianchain.guardianchain.model.ApiAbuse;
import lk.iit.guardianchain.guardianchain.service.ApiAbuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abuses")
public class ApiAbuseController {

    @Autowired
    private ApiAbuseService apiAbuseService;

    @PostMapping
    public ResponseEntity<ApiAbuse> reportApiAbuse(@RequestBody ApiAbuse apiAbuse) {
        return ResponseEntity.ok(apiAbuseService.reportApiAbuse(apiAbuse));
    }

    @GetMapping("/account/{accountHolderNIC}")
    public ResponseEntity<List<ApiAbuse>> getApiAbusesByAccountHolderNIC(@PathVariable String accountHolderNIC) {
        return ResponseEntity.ok(apiAbuseService.getApiAbusesByAccountHolderNIC(accountHolderNIC));
    }

    @GetMapping("/fi/{fiId}")
    public ResponseEntity<List<ApiAbuse>> getApiAbusesByFiId(@PathVariable String fiId) {
        return ResponseEntity.ok(apiAbuseService.getApiAbusesByFiId(fiId));
    }

    @GetMapping("/api/{apiContext}")
    public ResponseEntity<List<ApiAbuse>> getApiAbusesByApiContext(@PathVariable String apiContext) {
        return ResponseEntity.ok(apiAbuseService.getApiAbusesByApiContext(apiContext));
    }

    @GetMapping("/fi/{fiId}/api/{apiContext}")
    public ResponseEntity<List<ApiAbuse>> getApiAbusesByFiIdAndApiContext(
            @PathVariable String fiId,
            @PathVariable String apiContext) {
        return ResponseEntity.ok(apiAbuseService.getApiAbusesByFiIdAndApiContext(fiId, apiContext));
    }
} 