package lk.iit.guardianchain.guardianchain.service.impl;

import lk.iit.guardianchain.guardianchain.client.CreateIdentityResponse;
import lk.iit.guardianchain.guardianchain.client.EnrollIdentityResponse;
import lk.iit.guardianchain.guardianchain.model.ApiUser;
import lk.iit.guardianchain.guardianchain.repository.ApiUserRepository;
import lk.iit.guardianchain.guardianchain.service.ApiUserService;
import lk.iit.guardianchain.guardianchain.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApiUserServiceImpl implements ApiUserService {

    @Autowired
    private ApiUserRepository apiUserRepository;

    @Autowired
    private BlockchainService blockchainService;

    @Override
    public ApiUser createOrGetUser(String nic, String email) {
        // Check if user already exists
        Optional<ApiUser> existingUser = apiUserRepository.findByNic(nic);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        // Create new user
        ApiUser newUser = new ApiUser();
        newUser.setNic(nic);
        newUser.setEmail(email);
        newUser.setCreatedAt(LocalDateTime.now());

        // Create identity in blockchain
        CreateIdentityResponse createResponse = blockchainService.createIdentity(nic);
        newUser.setBlockchainSecret(createResponse.getSecret());

        // Enroll identity in blockchain
        EnrollIdentityResponse enrollResponse = blockchainService.enrollIdentity(nic, createResponse.getSecret());

        // Save user to database
        return apiUserRepository.save(newUser);
    }

    @Override
    public Optional<ApiUser> getUserByNic(String nic) {
        return apiUserRepository.findByNic(nic);
    }

    @Override
    public Optional<ApiUser> getUserByEmail(String email) {
        return apiUserRepository.findByEmail(email);
    }
} 