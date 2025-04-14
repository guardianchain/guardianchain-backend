package lk.iit.guardianchain.guardianchain.controller;

import lk.iit.guardianchain.guardianchain.model.ApiCategory;
import lk.iit.guardianchain.guardianchain.service.ApiCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {

    @Autowired
    private ApiCategoryService apiCategoryService;

    @GetMapping
    public ResponseEntity<List<ApiCategory>> getAllCategories() {
        return ResponseEntity.ok(apiCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(apiCategoryService.getCategoryById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiCategory> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(apiCategoryService.getCategoryByName(name));
    }

    @PostMapping
    public ResponseEntity<ApiCategory> createCategory(@RequestBody ApiCategory category) {
        return ResponseEntity.ok(apiCategoryService.createCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiCategory> updateCategory(@PathVariable Long id, @RequestBody ApiCategory category) {
        return ResponseEntity.ok(apiCategoryService.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        apiCategoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
} 