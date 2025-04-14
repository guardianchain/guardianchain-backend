package lk.iit.guardianchain.guardianchain.repository;

import lk.iit.guardianchain.guardianchain.model.ApiAbuse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ApiAbuseRepository extends JpaRepository<ApiAbuse, Long> {
    List<ApiAbuse> findByAccountHolderNIC(String accountHolderNIC);
    List<ApiAbuse> findByFiId(String fiId);
    List<ApiAbuse> findByApiContext(String apiContext);
    List<ApiAbuse> findByFiIdAndApiContext(String fiId, String apiContext);
    List<ApiAbuse> findByReportedDateTimeAfter(LocalDateTime date);
    long countByFiId(String fiId);
} 