package lk.iit.guardianchain.guardianchain.controller;

import lk.iit.guardianchain.guardianchain.dto.UserDTO;
import lk.iit.guardianchain.guardianchain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/financial-institution/{financialInstitutionId}")
    public ResponseEntity<List<UserDTO>> getUsersByFinancialInstitution(@PathVariable Long financialInstitutionId) {
        return ResponseEntity.ok(userService.getUsersByFinancialInstitution(financialInstitutionId));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
} 