package lk.iit.guardianchain.guardianchain.service;

import lk.iit.guardianchain.guardianchain.model.ApiAbuse;
import java.util.List;

public interface ApiAbuseService {
    ApiAbuse reportApiAbuse(ApiAbuse apiAbuse);
    List<ApiAbuse> getApiAbusesByAccountHolderNIC(String accountHolderNIC);
    List<ApiAbuse> getApiAbusesByFiId(String fiId);
    List<ApiAbuse> getApiAbusesByApiContext(String apiContext);
    List<ApiAbuse> getApiAbusesByFiIdAndApiContext(String fiId, String apiContext);
} 