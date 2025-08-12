package com.mywork.newsletter.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "news_letter_subscription_table")
@NamedQuery(name = "findAll", query = "select n from NewsLetterEntity n")
public class NewsLetterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String Gender;

    @Column(name = "topic")
    private String topic;
}
