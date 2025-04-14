package lk.iit.guardianchain.guardianchain.config;

import lk.iit.guardianchain.guardianchain.enums.UserRole;
import lk.iit.guardianchain.guardianchain.model.User;
import lk.iit.guardianchain.guardianchain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Check if admin user exists
        if (!userRepository.existsByEmail("admin@guardianchain.com")) {
            User adminUser = new User();
            adminUser.setFirstname("Super");
            adminUser.setLastname("Admin");
            adminUser.setEmail("admin@guardianchain.com");
            adminUser.setPassword(passwordEncoder.encode("abcd@123"));
            adminUser.setRole(UserRole.SUPER_ADMIN);
            userRepository.save(adminUser);
            System.out.println("Super admin user created successfully!");
        }
    }
} 