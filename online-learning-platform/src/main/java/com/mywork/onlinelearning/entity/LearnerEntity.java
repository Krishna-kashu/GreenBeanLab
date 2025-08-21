package com.mywork.onlinelearning.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@NamedQuery(name = "checkMail", query = "select l.email from LearnerEntity l where l.email=:email and l.isActive=true")
@NamedQuery(name = "checkPhone", query = "select l.phone from LearnerEntity l where l.phone=:phone and l.isActive=true")
@NamedQuery(name = "getByEmail", query = "select l from LearnerEntity l where l.email=:email and l.isActive=true")
@Table(name = "online_learning_platform")
public class LearnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learner_id")
    private Integer learnerId;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private String dob;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "state")
    private String state;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name="otp")
    private String otp;

    @Column(name="otp_expiry")
    private LocalDateTime otpExpiry;

    @Column(name="reset_flag")
    private Integer resetFlag;

    @Column(name="first_login_done")
    private Boolean firstLoginDone = false;

}