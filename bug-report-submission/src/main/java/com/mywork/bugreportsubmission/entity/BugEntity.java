package com.mywork.bugreportsubmission.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "bug_report_table")
@NamedQuery(name = "findAll", query = "select b from BugEntity b")
public class BugEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "reporter_name")
    private String reporterName;

    @Column(name = "email")
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "steps_count")
    private Integer stepsCount;

    @Column(name = "is_critical")
    private Boolean isCritical;
}
