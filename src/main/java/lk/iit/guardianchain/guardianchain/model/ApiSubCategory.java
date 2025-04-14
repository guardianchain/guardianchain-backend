package lk.iit.guardianchain.guardianchain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "api_sub_categories")
public class ApiSubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ApiCategory category;

    @JsonManagedReference
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private List<OpenBankingApi> apis;
} 