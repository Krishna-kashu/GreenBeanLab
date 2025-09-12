package com.mywork.usermanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_profile")
public class UserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "address")
    private String address;

    @Column(name = "bio")
    private String bio;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
