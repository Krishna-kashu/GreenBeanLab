package com.mywork.dairy360.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "admin_details",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "phone_number")
        })
@NamedQueries({
        @NamedQuery(name = "getPasswordByEmail",
                query = "SELECT a FROM AdminEntity a WHERE a.email = :email"),
        })
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "admin_name", nullable = false)
    private String adminName;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "failed_attempts")
    private Integer failedAttempts =0;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked = true;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "lock_time")
    private LocalDateTime lockTime;

    @Column(name = "token_expiry")
    private LocalDateTime tokenExpiry;

    @Column(name = "profile_image_path")
    private String profileImagePath;

}
