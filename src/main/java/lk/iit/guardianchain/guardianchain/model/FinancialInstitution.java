package lk.iit.guardianchain.guardianchain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "financial_institutions")
public class FinancialInstitution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String logo;

    @OneToMany(mappedBy = "financialInstitution", cascade = CascadeType.ALL)
    private List<User> users;
} 