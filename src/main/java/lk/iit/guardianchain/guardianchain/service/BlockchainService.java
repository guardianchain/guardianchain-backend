package lk.iit.guardianchain.guardianchain.service;

import lk.iit.guardianchain.guardianchain.client.BlockchainResponse;
import lk.iit.guardianchain.guardianchain.client.CreateIdentityResponse;
import lk.iit.guardianchain.guardianchain.client.EnrollIdentityResponse;

public interface BlockchainService {
    BlockchainResponse mintToken(String userNIC, Long apiId);
    CreateIdentityResponse createIdentity(String userNIC);
    EnrollIdentityResponse enrollIdentity(String userNIC, String secret);
} 