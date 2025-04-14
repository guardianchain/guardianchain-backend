package lk.iit.guardianchain.guardianchain.service;

import lk.iit.guardianchain.guardianchain.model.ApiCategory;
import java.util.List;

public interface ApiCategoryService {
    List<ApiCategory> getAllCategories();
    ApiCategory getCategoryById(Long id);
    ApiCategory getCategoryByName(String name);
    ApiCategory createCategory(ApiCategory category);
    ApiCategory updateCategory(Long id, ApiCategory category);
    void deleteCategory(Long id);
} 