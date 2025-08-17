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
@NamedQuery(name = "updateBugEntity", query = "update BugEntity b set b.reporterName=:reporterName, b.email=:email, " +
        "b.title=:title, b.description=:description, b.stepsCount=:stepsCount, b.isCritical=:isCritical where b.id=:id")
@NamedQuery(name = "deleteById", query = "delete from BugEntity b where b.id=:id")
@NamedQuery(name = "findByReporterName", query = "select b from BugEntity b where b.reporterName=:reporterName ")
@NamedQuery(name = "checkMail", query = "select b from BugEntity b where b.email=:email")
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
