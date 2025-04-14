package lk.iit.guardianchain.guardianchain.service;

import lk.iit.guardianchain.guardianchain.dto.FinancialInstitutionDTO;
import lk.iit.guardianchain.guardianchain.model.FinancialInstitution;
import lk.iit.guardianchain.guardianchain.repository.FinancialInstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialInstitutionService {
    
    @Autowired
    private FinancialInstitutionRepository financialInstitutionRepository;

    /**
     * Get all financial institutions
     * @return List of financial institution DTOs
     */
    public List<FinancialInstitutionDTO> getAllFinancialInstitutions() {
        return financialInstitutionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a financial institution by ID
     * @param id Financial institution ID
     * @return Financial institution DTO
     * @throws RuntimeException if financial institution not found
     */
    public FinancialInstitutionDTO getFinancialInstitution(Long id) {
        return financialInstitutionRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Financial Institution not found with ID: " + id));
    }

    /**
     * Create a new financial institution
     * @param dto Financial institution data
     * @return Created financial institution DTO
     * @throws RuntimeException if name is empty or already exists
     */
    @Transactional
    public FinancialInstitutionDTO createFinancialInstitution(FinancialInstitutionDTO dto) {
        validateFinancialInstitutionData(dto);
        
        if (financialInstitutionRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Financial Institution with name '" + dto.getName() + "' already exists");
        }

        FinancialInstitution fi = new FinancialInstitution();
        fi.setName(dto.getName());
        fi.setLogo(dto.getLogo());

        return convertToDTO(financialInstitutionRepository.save(fi));
    }

    /**
     * Update an existing financial institution
     * @param id Financial institution ID
     * @param dto Updated financial institution data
     * @return Updated financial institution DTO
     * @throws RuntimeException if financial institution not found or name already exists
     */
    @Transactional
    public FinancialInstitutionDTO updateFinancialInstitution(Long id, FinancialInstitutionDTO dto) {
        validateFinancialInstitutionData(dto);
        
        FinancialInstitution fi = financialInstitutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Financial Institution not found with ID: " + id));
        
        // Check if the new name is different and already exists
        if (!fi.getName().equals(dto.getName()) && financialInstitutionRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Financial Institution with name '" + dto.getName() + "' already exists");
        }

        fi.setName(dto.getName());
        fi.setLogo(dto.getLogo());

        return convertToDTO(financialInstitutionRepository.save(fi));
    }

    /**
     * Delete a financial institution
     * @param id Financial institution ID
     * @throws RuntimeException if financial institution not found
     */
    @Transactional
    public void deleteFinancialInstitution(Long id) {
        if (!financialInstitutionRepository.existsById(id)) {
            throw new RuntimeException("Financial Institution not found with ID: " + id);
        }
        financialInstitutionRepository.deleteById(id);
    }

    /**
     * Validate financial institution data
     * @param dto Financial institution data to validate
     * @throws RuntimeException if validation fails
     */
    private void validateFinancialInstitutionData(FinancialInstitutionDTO dto) {
        if (dto == null) {
            throw new RuntimeException("Financial Institution data cannot be null");
        }
        
        if (!StringUtils.hasText(dto.getName())) {
            throw new RuntimeException("Financial Institution name cannot be empty");
        }
    }

    /**
     * Convert FinancialInstitution entity to DTO
     * @param fi Financial institution entity
     * @return Financial institution DTO
     */
    private FinancialInstitutionDTO convertToDTO(FinancialInstitution fi) {
        FinancialInstitutionDTO dto = new FinancialInstitutionDTO();
        dto.setId(fi.getId());
        dto.setName(fi.getName());
        dto.setLogo(fi.getLogo());
        return dto;
    }
} 