package com.mywork.dairy360.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "seller_details")
@NamedQuery(name = "findAll", query = "SELECT s FROM SellerEntity s WHERE s.active = true")
@NamedQuery(name = "checkEmail", query = "SELECT a FROM SellerEntity a WHERE a.email =:email")
@NamedQuery(name = "byMail", query = "SELECT COUNT(s) FROM SellerEntity s WHERE s.email =:email")
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Integer sellerId;

    @NotBlank(message = "First name cannot be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @NotBlank(message = "Milk type required")
    @Column(name = "milk_type")
    private String milkType;

    @Column(name = "is_active")
    private Boolean active = true;
}
