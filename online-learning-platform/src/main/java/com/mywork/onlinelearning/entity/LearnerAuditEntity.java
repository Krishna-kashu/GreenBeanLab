package com.mywork.onlinelearning.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "learner_audit")
public class LearnerAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Integer auditId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "learner_id", nullable = false)
    private LearnerEntity learner;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on", columnDefinition = "DATETIME")
    private LocalDateTime updatedOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on", columnDefinition = "DATETIME")
    private LocalDateTime createdOn;
}
