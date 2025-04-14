package lk.iit.guardianchain.guardianchain.repository;

import lk.iit.guardianchain.guardianchain.model.ApiSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiSubCategoryRepository extends JpaRepository<ApiSubCategory, Long> {
    ApiSubCategory findByNameAndCategory_Id(String name, Long categoryId);
} 