package lk.iit.guardianchain.guardianchain.model;

import jakarta.persistence.*;
import lombok.Data;
import lk.iit.guardianchain.guardianchain.enums.UserRole;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "financial_institution_id")
    private FinancialInstitution financialInstitution;
} 