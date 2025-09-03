package com.mywork.usermanagement.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "user_details")
@NamedQueries({
        @NamedQuery(name = "allUser", query = "select u from UserEntity u"),
        @NamedQuery(name = "deleteById", query = "delete UserEntity u where u.userId=:id"),
        @NamedQuery(name = "updateEntity", query = "update UserEntity u set u.userName=:userName, u.gender=:gender, " +
                "u.age=:age, u.phoneNumber=:phoneNumber, u.email=:email where userId=:id")
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "email")
    private String  email;
}
