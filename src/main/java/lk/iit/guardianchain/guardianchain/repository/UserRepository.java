package lk.iit.guardianchain.guardianchain.repository;

import lk.iit.guardianchain.guardianchain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    List<User> findByFinancialInstitutionId(Long financialInstitutionId);
    Optional<User> findByEmail(String email);
} 