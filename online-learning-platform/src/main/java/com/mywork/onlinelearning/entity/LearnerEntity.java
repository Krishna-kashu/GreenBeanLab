package com.mywork.onlinelearning.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "online_learning_platform")
@NamedQueries({
        @NamedQuery( name = "checkMail", query = "select l.email from LearnerEntity l where l.email=:email and l.isActive=true"
        ),
        @NamedQuery( name = "checkPhone", query = "select l.phone from LearnerEntity l where l.phone=:phone and l.isActive=true"
        ),
        @NamedQuery( name = "getByEmail", query = "select l from LearnerEntity l where l.email=:email and l.isActive=true"
        ),
        @NamedQuery(name = "updateDTO", query = "update LearnerEntity l set l.name=:name, l.gender=:gender, l.dob=:dob, l.phone=:phone, l.state=:state, l.address=:address, l.password=:password where l.learnerId=:id"
        ),
})
public class LearnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learner_id")
    private Integer learnerId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private LocalDate dob;

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

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "login_count")
    private Integer loginCount;

    @Column(name = "lock_time", columnDefinition = "DATETIME")
    private LocalDateTime lockTime;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private String pincode;

//    @Column(name = "last_otp_sent_time")
//    private LocalDateTime lastOtpSentTime;
}
