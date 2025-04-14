package lk.iit.guardianchain.guardianchain.repository;

import lk.iit.guardianchain.guardianchain.model.FinancialInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialInstitutionRepository extends JpaRepository<FinancialInstitution, Long> {
    boolean existsByName(String name);
} 