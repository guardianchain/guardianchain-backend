package lk.iit.guardianchain.guardianchain.controller;

import lk.iit.guardianchain.guardianchain.dto.FinancialInstitutionDTO;
import lk.iit.guardianchain.guardianchain.service.FinancialInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financial-institutions")
public class FinancialInstitutionController {

    @Autowired
    private FinancialInstitutionService financialInstitutionService;

    @GetMapping
    public ResponseEntity<List<FinancialInstitutionDTO>> getAllFinancialInstitutions() {
        return ResponseEntity.ok(financialInstitutionService.getAllFinancialInstitutions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialInstitutionDTO> getFinancialInstitution(@PathVariable Long id) {
        return ResponseEntity.ok(financialInstitutionService.getFinancialInstitution(id));
    }

    @PostMapping
    public ResponseEntity<FinancialInstitutionDTO> createFinancialInstitution(@RequestBody FinancialInstitutionDTO dto) {
        return ResponseEntity.ok(financialInstitutionService.createFinancialInstitution(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialInstitutionDTO> updateFinancialInstitution(
            @PathVariable Long id,
            @RequestBody FinancialInstitutionDTO dto) {
        return ResponseEntity.ok(financialInstitutionService.updateFinancialInstitution(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinancialInstitution(@PathVariable Long id) {
        financialInstitutionService.deleteFinancialInstitution(id);
        return ResponseEntity.ok().build();
    }
} 