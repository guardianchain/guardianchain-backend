package lk.iit.guardianchain.guardianchain.service.impl;

import lk.iit.guardianchain.guardianchain.model.ApiCategory;
import lk.iit.guardianchain.guardianchain.repository.ApiCategoryRepository;
import lk.iit.guardianchain.guardianchain.service.ApiCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiCategoryServiceImpl implements ApiCategoryService {

    @Autowired
    private ApiCategoryRepository apiCategoryRepository;

    @Override
    public List<ApiCategory> getAllCategories() {
        return apiCategoryRepository.findAll();
    }

    @Override
    public ApiCategory getCategoryById(Long id) {
        return apiCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    public ApiCategory getCategoryByName(String name) {
        return apiCategoryRepository.findByName(name);
    }

    @Override
    public ApiCategory createCategory(ApiCategory category) {
        return apiCategoryRepository.save(category);
    }

    @Override
    public ApiCategory updateCategory(Long id, ApiCategory category) {
        ApiCategory existingCategory = getCategoryById(id);
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        return apiCategoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        apiCategoryRepository.deleteById(id);
    }
} 