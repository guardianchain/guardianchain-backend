package lk.iit.guardianchain.guardianchain.repository;

import lk.iit.guardianchain.guardianchain.model.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ApiUserRepository extends JpaRepository<ApiUser, Long> {
    Optional<ApiUser> findByNic(String nic);
    Optional<ApiUser> findByEmail(String email);
} 