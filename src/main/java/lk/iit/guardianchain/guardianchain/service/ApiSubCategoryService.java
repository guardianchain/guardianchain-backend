package lk.iit.guardianchain.guardianchain.service;

import lk.iit.guardianchain.guardianchain.model.ApiSubCategory;
import java.util.List;

public interface ApiSubCategoryService {
    List<ApiSubCategory> getAllSubCategories();
    List<ApiSubCategory> getSubCategoriesByCategoryId(Long categoryId);
    ApiSubCategory getSubCategoryById(Long id);
    ApiSubCategory getSubCategoryByNameAndCategoryId(String name, Long categoryId);
    ApiSubCategory createSubCategory(ApiSubCategory subCategory);
    ApiSubCategory updateSubCategory(Long id, ApiSubCategory subCategory);
    void deleteSubCategory(Long id);
} 