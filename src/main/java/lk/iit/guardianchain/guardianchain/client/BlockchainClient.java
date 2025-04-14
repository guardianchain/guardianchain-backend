package lk.iit.guardianchain.guardianchain.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "blockchain", url = "https://u0wk8yxlsn-u0pmi0c6nm-connect.us0-aws-ws.kaleido.io")
public interface BlockchainClient {

    @PostMapping("/transactions")
    BlockchainResponse mintToken(
        @RequestHeader("Authorization") String authorization,
        @RequestBody BlockchainRequest request
    );
    
    @PostMapping("/identities")
    CreateIdentityResponse createIdentity(
        @RequestHeader("Authorization") String authorization,
        @RequestBody CreateIdentityRequest request
    );
    
    @PostMapping("/identities/{name}/enroll")
    EnrollIdentityResponse enrollIdentity(
        @RequestHeader("Authorization") String authorization,
        @PathVariable("name") String name,
        @RequestBody EnrollIdentityRequest request
    );
} 