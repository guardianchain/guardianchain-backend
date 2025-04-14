package lk.iit.guardianchain.guardianchain.service;

import lk.iit.guardianchain.guardianchain.dto.UserDTO;
import lk.iit.guardianchain.guardianchain.model.User;
import lk.iit.guardianchain.guardianchain.model.FinancialInstitution;
import lk.iit.guardianchain.guardianchain.repository.UserRepository;
import lk.iit.guardianchain.guardianchain.repository.FinancialInstitutionRepository;
import lk.iit.guardianchain.guardianchain.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FinancialInstitutionRepository financialInstitutionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Get all users
     * @return List of user DTOs
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a user by ID
     * @param id User ID
     * @return User DTO
     * @throws RuntimeException if user not found
     */
    public UserDTO getUser(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    /**
     * Get a user by email
     * @param email User email
     * @return User DTO
     * @throws RuntimeException if user not found
     */
    public UserDTO getUserByEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new RuntimeException("Email cannot be empty");
        }
        
        return userRepository.findByEmail(email)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    /**
     * Create a new user
     * @param dto User data
     * @return Created user DTO
     * @throws RuntimeException if validation fails or email already exists
     */
    @Transactional
    public UserDTO createUser(UserDTO dto) {
        validateUserData(dto);
        
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("User with email '" + dto.getEmail() + "' already exists");
        }

        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Set financial institution if provided
        if (dto.getFinancialInstitutionId() != null) {
            FinancialInstitution fi = financialInstitutionRepository.findById(dto.getFinancialInstitutionId())
                    .orElseThrow(() -> new RuntimeException("Financial Institution not found with ID: " + dto.getFinancialInstitutionId()));
            user.setFinancialInstitution(fi);
        } else if (dto.getRole() != UserRole.SUPER_ADMIN) {
            // Non-super admin users must belong to a financial institution
            throw new RuntimeException("Non-super admin users must belong to a financial institution");
        }

        return convertToDTO(userRepository.save(user));
    }

    /**
     * Update an existing user
     * @param id User ID
     * @param dto Updated user data
     * @return Updated user DTO
     * @throws RuntimeException if user not found or validation fails
     */
    @Transactional
    public UserDTO updateUser(Long id, UserDTO dto) {
        validateUserData(dto);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        
        // Check if the new email is different and already exists
        if (!user.getEmail().equals(dto.getEmail()) && userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("User with email '" + dto.getEmail() + "' already exists");
        }

        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        // Update financial institution if provided
        if (dto.getFinancialInstitutionId() != null) {
            FinancialInstitution fi = financialInstitutionRepository.findById(dto.getFinancialInstitutionId())
                    .orElseThrow(() -> new RuntimeException("Financial Institution not found with ID: " + dto.getFinancialInstitutionId()));
            user.setFinancialInstitution(fi);
        } else if (dto.getRole() != UserRole.SUPER_ADMIN) {
            // Non-super admin users must belong to a financial institution
            throw new RuntimeException("Non-super admin users must belong to a financial institution");
        } else {
            // Clear financial institution for super admin
            user.setFinancialInstitution(null);
        }

        return convertToDTO(userRepository.save(user));
    }

    /**
     * Delete a user
     * @param id User ID
     * @throws RuntimeException if user not found
     */
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    /**
     * Get users by financial institution ID
     * @param financialInstitutionId Financial institution ID
     * @return List of user DTOs
     */
    public List<UserDTO> getUsersByFinancialInstitution(Long financialInstitutionId) {
        return userRepository.findByFinancialInstitutionId(financialInstitutionId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Validate user data
     * @param dto User data to validate
     * @throws RuntimeException if validation fails
     */
    private void validateUserData(UserDTO dto) {
        if (dto == null) {
            throw new RuntimeException("User data cannot be null");
        }
        
        if (!StringUtils.hasText(dto.getFirstname())) {
            throw new RuntimeException("First name cannot be empty");
        }
        
        if (!StringUtils.hasText(dto.getLastname())) {
            throw new RuntimeException("Last name cannot be empty");
        }
        
        if (!StringUtils.hasText(dto.getEmail())) {
            throw new RuntimeException("Email cannot be empty");
        }
        
        if (!EMAIL_PATTERN.matcher(dto.getEmail()).matches()) {
            throw new RuntimeException("Invalid email format");
        }
        
        if (dto.getRole() == null) {
            throw new RuntimeException("User role cannot be null");
        }
    }

    /**
     * Convert User entity to DTO
     * @param user User entity
     * @return User DTO
     */
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        if (user.getFinancialInstitution() != null) {
            dto.setFinancialInstitutionId(user.getFinancialInstitution().getId());
        }
        return dto;
    }
} 