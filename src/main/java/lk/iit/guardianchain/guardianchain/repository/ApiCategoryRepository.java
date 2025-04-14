package lk.iit.guardianchain.guardianchain.repository;

import lk.iit.guardianchain.guardianchain.model.ApiCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiCategoryRepository extends JpaRepository<ApiCategory, Long> {
    ApiCategory findByName(String name);
} 