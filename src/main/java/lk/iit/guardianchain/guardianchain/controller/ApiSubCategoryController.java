package lk.iit.guardianchain.guardianchain.controller;

import lk.iit.guardianchain.guardianchain.model.ApiSubCategory;
import lk.iit.guardianchain.guardianchain.service.ApiSubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
public class ApiSubCategoryController {

    @Autowired
    private ApiSubCategoryService apiSubCategoryService;

    @GetMapping
    public ResponseEntity<List<ApiSubCategory>> getAllSubCategories() {
        return ResponseEntity.ok(apiSubCategoryService.getAllSubCategories());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ApiSubCategory>> getSubCategoriesByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(apiSubCategoryService.getSubCategoriesByCategoryId(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiSubCategory> getSubCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(apiSubCategoryService.getSubCategoryById(id));
    }

    @GetMapping("/name/{name}/category/{categoryId}")
    public ResponseEntity<ApiSubCategory> getSubCategoryByNameAndCategoryId(
            @PathVariable String name, @PathVariable Long categoryId) {
        return ResponseEntity.ok(apiSubCategoryService.getSubCategoryByNameAndCategoryId(name, categoryId));
    }

    @PostMapping
    public ResponseEntity<ApiSubCategory> createSubCategory(@RequestBody ApiSubCategory subCategory) {
        return ResponseEntity.ok(apiSubCategoryService.createSubCategory(subCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiSubCategory> updateSubCategory(@PathVariable Long id, @RequestBody ApiSubCategory subCategory) {
        return ResponseEntity.ok(apiSubCategoryService.updateSubCategory(id, subCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        apiSubCategoryService.deleteSubCategory(id);
        return ResponseEntity.ok().build();
    }
} 