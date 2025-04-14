package lk.iit.guardianchain.guardianchain.service.impl;

import lk.iit.guardianchain.guardianchain.model.ApiCategory;
import lk.iit.guardianchain.guardianchain.model.ApiSubCategory;
import lk.iit.guardianchain.guardianchain.repository.ApiCategoryRepository;
import lk.iit.guardianchain.guardianchain.repository.ApiSubCategoryRepository;
import lk.iit.guardianchain.guardianchain.service.ApiSubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiSubCategoryServiceImpl implements ApiSubCategoryService {

    @Autowired
    private ApiSubCategoryRepository apiSubCategoryRepository;

    @Autowired
    private ApiCategoryRepository apiCategoryRepository;

    @Override
    public List<ApiSubCategory> getAllSubCategories() {
        return apiSubCategoryRepository.findAll();
    }

    @Override
    public List<ApiSubCategory> getSubCategoriesByCategoryId(Long categoryId) {
        return apiSubCategoryRepository.findAll().stream()
                .filter(subCategory -> subCategory.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }

    @Override
    public ApiSubCategory getSubCategoryById(Long id) {
        return apiSubCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategory not found with id: " + id));
    }

    @Override
    public ApiSubCategory getSubCategoryByNameAndCategoryId(String name, Long categoryId) {
        return apiSubCategoryRepository.findByNameAndCategory_Id(name, categoryId);
    }

    @Override
    public ApiSubCategory createSubCategory(ApiSubCategory subCategory) {
        return apiSubCategoryRepository.save(subCategory);
    }

    @Override
    public ApiSubCategory updateSubCategory(Long id, ApiSubCategory subCategory) {
        ApiSubCategory existingSubCategory = getSubCategoryById(id);
        existingSubCategory.setName(subCategory.getName());
        existingSubCategory.setDescription(subCategory.getDescription());
        return apiSubCategoryRepository.save(existingSubCategory);
    }

    @Override
    public void deleteSubCategory(Long id) {
        apiSubCategoryRepository.deleteById(id);
    }
} 