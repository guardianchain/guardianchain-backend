package lk.iit.guardianchain.guardianchain.service.impl;

import lk.iit.guardianchain.guardianchain.client.BlockchainResponse;
import lk.iit.guardianchain.guardianchain.model.ApiAbuse;
import lk.iit.guardianchain.guardianchain.model.ApiUser;
import lk.iit.guardianchain.guardianchain.repository.ApiAbuseRepository;
import lk.iit.guardianchain.guardianchain.service.ApiAbuseService;
import lk.iit.guardianchain.guardianchain.service.ApiUserService;
import lk.iit.guardianchain.guardianchain.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApiAbuseServiceImpl implements ApiAbuseService {

    @Autowired
    private ApiAbuseRepository apiAbuseRepository;

    @Autowired
    private BlockchainService blockchainService;
    
    @Autowired
    private ApiUserService apiUserService;

    @Override
    public ApiAbuse reportApiAbuse(ApiAbuse apiAbuse) {
        // Set the reported date time to now
        apiAbuse.setReportedDateTime(LocalDateTime.now());

        // Create or get the API user
        ApiUser apiUser = apiUserService.createOrGetUser(
            apiAbuse.getAccountHolderNIC(), 
            apiAbuse.getAccountHolderEmail()
        );
        
        // Set the API user
        apiAbuse.setApiUser(apiUser);
        apiAbuse = apiAbuseRepository.save(apiAbuse);

        // Call the blockchain service to mint a token
        BlockchainResponse response = blockchainService.mintToken(
            apiAbuse.getAccountHolderNIC(), 
            apiAbuse.getId()
        );

        // Set the blockchain response details
        apiAbuse.setBlockId(response.getTransactionID());
        apiAbuse.setTransactionId(response.getTransactionID());
        apiAbuse.setBlockNumber(response.getBlockNumber());

        // Save the API abuse report
        return apiAbuseRepository.save(apiAbuse);
    }

    @Override
    public List<ApiAbuse> getApiAbusesByAccountHolderNIC(String accountHolderNIC) {
        return apiAbuseRepository.findByAccountHolderNIC(accountHolderNIC);
    }

    @Override
    public List<ApiAbuse> getApiAbusesByFiId(String fiId) {
        return apiAbuseRepository.findByFiId(fiId);
    }

    @Override
    public List<ApiAbuse> getApiAbusesByApiContext(String apiContext) {
        return apiAbuseRepository.findByApiContext(apiContext);
    }

    @Override
    public List<ApiAbuse> getApiAbusesByFiIdAndApiContext(String fiId, String apiContext) {
        return apiAbuseRepository.findByFiIdAndApiContext(fiId, apiContext);
    }
} 