package lk.iit.guardianchain.guardianchain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "api_users")
public class ApiUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nic;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private String blockchainSecret;
} 