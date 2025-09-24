package com.mywork.dairy360.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "register_table")
@NamedQuery(name = "checkMail", query = "SELECT a FROM RegisterEntity a WHERE a.email = :email")
@NamedQuery(name = "findByMail", query = "select r from RegisterEntity r where r.resetToken = :token")
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "failed_attempts")
    private Integer failedAttempts = 0;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked = true;

    @Column(name = "lock_time")
    private LocalDateTime lockTime;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expiry")
    private LocalDateTime resetTokenExpiry;

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_expiry")
    private LocalDateTime otpExpiry;

//    @Column(name = "wallet_balance")
//    private Double walletBalance;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<MilkCollectionEntity> milkCollections;
//
//    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
//    private List<OrderEntity> orders;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<PaymentEntity> payments;
//
//    @OneToMany(mappedBy = "deliveryStaff", cascade = CascadeType.ALL)
//    private List<DeliveryEntity> deliveries;
}