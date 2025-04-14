package lk.iit.guardianchain.guardianchain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "api_abuses")
public class ApiAbuse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime reportedDateTime;
    private String apiContext;
    private String blockId;
    private String accountHolderNIC;
    private String accountHolderEmail;

    @Column(length = 2000)
    private String description;
    private String type;
    private String fiId;
    private String transactionId;
    private Long blockNumber;
    
    @ManyToOne
    @JoinColumn(name = "api_user_id")
    private ApiUser apiUser;
} 