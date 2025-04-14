package lk.iit.guardianchain.guardianchain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "open_banking_apis")
public class OpenBankingApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String context;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ApiCategory category;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private ApiSubCategory subCategory;
} 