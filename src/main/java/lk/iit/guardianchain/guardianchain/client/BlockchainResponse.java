package lk.iit.guardianchain.guardianchain.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockchainResponse {
    private Headers headers;
    private Long blockNumber;
    private String signerMSP;
    private String signer;
    private String transactionID;
    private String status;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Headers {
        private String id;
        private String type;
        private String timeReceived;
        private Double timeElapsed;
        private String requestOffset;
        private String requestId;
    }
} 