package lk.iit.guardianchain.guardianchain.controller;

import lk.iit.guardianchain.guardianchain.model.ApiUser;
import lk.iit.guardianchain.guardianchain.service.ApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Autowired
    private ApiUserService apiUserService;

    @GetMapping("/nic/{nic}")
    public ResponseEntity<ApiUser> getUserByNic(@PathVariable String nic) {
        Optional<ApiUser> user = apiUserService.getUserByNic(nic);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiUser> getUserByEmail(@PathVariable String email) {
        Optional<ApiUser> user = apiUserService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 