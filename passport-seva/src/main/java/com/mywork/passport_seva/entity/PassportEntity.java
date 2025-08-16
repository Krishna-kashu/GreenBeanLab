package com.mywork.passport_seva.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "passport_user_details")
@NamedQueries({
        @NamedQuery( name = "findAll", query = "select p from PassportEntity p"),
        @NamedQuery(name = "checkMail", query = "select p from PassportEntity p where p.email=:email"),
        @NamedQuery(name = "checkNumber", query = "select p from PassportEntity p where p.phone =:phone"),
        })

public class PassportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "passport_office")
    private String passportOffice;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "sur_name")
    private  String surName;

    @Column(name = "dob")
    private String dob;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "same_login_id")
    private boolean sameLoginId;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "hintQuestion")
    private String hintQuestion;

    @Column(name = "hintAnswer")
    private String hintAnswer;
}
