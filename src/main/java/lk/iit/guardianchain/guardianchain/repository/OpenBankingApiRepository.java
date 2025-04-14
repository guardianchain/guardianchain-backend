package lk.iit.guardianchain.guardianchain.repository;

import lk.iit.guardianchain.guardianchain.model.OpenBankingApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenBankingApiRepository extends JpaRepository<OpenBankingApi, Long> {
    OpenBankingApi findByName(String name);
} 