package lk.iit.guardianchain.guardianchain.controller;

import lk.iit.guardianchain.guardianchain.dto.LoginRequest;
import lk.iit.guardianchain.guardianchain.dto.LoginResponse;
import lk.iit.guardianchain.guardianchain.dto.UserDTO;
import lk.iit.guardianchain.guardianchain.security.JwtUtil;
import lk.iit.guardianchain.guardianchain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        UserDTO user = userService.getUserByEmail(userDetails.getUsername());

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUser(user);

        return ResponseEntity.ok(response);
    }
} 