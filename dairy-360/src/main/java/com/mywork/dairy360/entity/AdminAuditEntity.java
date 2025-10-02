package com.mywork.dairy360.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "admin_audit")

@NamedQuery(name = "updateLogoutTime",query = "update AdminAuditEntity a set a.logoutTime=: logoutTime where a.id=:id")
@NamedQuery(name = "getAll", query = "SELECT a FROM AdminAuditEntity a WHERE a.admin = :admin ORDER BY a.loginTime DESC")
public class AdminAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminEntity admin;

    @Column(name = "admin_name", nullable = false)
    private String adminName;

    @Column(name = "login_time")
    private LocalDateTime loginTime;

    @Column(name = "logout_time")
    private LocalDateTime logoutTime;

}
