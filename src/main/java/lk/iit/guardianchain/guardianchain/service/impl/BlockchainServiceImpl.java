package lk.iit.guardianchain.guardianchain.service.impl;

import lk.iit.guardianchain.guardianchain.client.BlockchainClient;
import lk.iit.guardianchain.guardianchain.client.BlockchainRequest;
import lk.iit.guardianchain.guardianchain.client.BlockchainResponse;
import lk.iit.guardianchain.guardianchain.client.CreateIdentityRequest;
import lk.iit.guardianchain.guardianchain.client.CreateIdentityResponse;
import lk.iit.guardianchain.guardianchain.client.EnrollIdentityRequest;
import lk.iit.guardianchain.guardianchain.client.EnrollIdentityResponse;
import lk.iit.guardianchain.guardianchain.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class BlockchainServiceImpl implements BlockchainService {

    @Autowired
    private BlockchainClient blockchainClient;

    @Value("${blockchain.authorization}")
    private String authorization;

    @Value("${blockchain.signer}")
    private String signer;

    @Value("${blockchain.channel}")
    private String channel;

    @Value("${blockchain.chaincode}")
    private String chaincode;

    @Override
    public BlockchainResponse mintToken(String userNIC, Long apiId) {
        BlockchainRequest request = BlockchainRequest.builder()
            .headers(BlockchainRequest.Headers.builder()
                .signer(signer)
                .channel(channel)
                .chaincode(chaincode)
                .build())
            .func("Mint")
            .args(Arrays.asList(userNIC, apiId.toString(), "1"))
            .init(false)
            .build();

        return blockchainClient.mintToken("Basic " + authorization, request);
    }

    @Override
    public CreateIdentityResponse createIdentity(String userNIC) {
        CreateIdentityRequest request = CreateIdentityRequest.builder()
            .name(userNIC)
            .type("client")
            .maxEnrollments(0)
            .attributes(new HashMap<>())
            .build();

        return blockchainClient.createIdentity("Basic " + authorization, request);
    }

    @Override
    public EnrollIdentityResponse enrollIdentity(String userNIC, String secret) {
        EnrollIdentityRequest request = EnrollIdentityRequest.builder()
            .secret(secret)
            .attributes(new HashMap<>())
            .build();

        return blockchainClient.enrollIdentity("Basic " + authorization, userNIC, request);
    }
} 