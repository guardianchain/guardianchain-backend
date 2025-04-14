package lk.iit.guardianchain.guardianchain.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockchainRequest {
    private Headers headers;
    private String func;
    private List<String> args;
    private boolean init;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Headers {
        private String signer;
        private String channel;
        private String chaincode;
    }
} 