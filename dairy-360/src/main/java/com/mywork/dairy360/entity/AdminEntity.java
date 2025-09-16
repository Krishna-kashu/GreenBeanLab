package com.mywork.dairy360.entity;

import lombok.Data;

import javax.persistence.*;

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

    @Transient
    private String confirmPassword;

}
