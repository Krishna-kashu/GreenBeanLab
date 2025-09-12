package com.mywork.usermanagement.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "user_details")
@Where(clause = "is_deleted = false")
@NamedQueries({
        @NamedQuery(name = "allUser", query = "select u from UserEntity u"),
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

    @Column(name = "is_deleted")
    private boolean deleted = false;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "email")
    private String  email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AuditInfoEntity> audits = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserProfileEntity profile;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    @PrePersist
    public void onPrePersist() {
        AuditInfoEntity audit = new AuditInfoEntity();
        audit.setUser(this);
        audit.setUpdatedBy(this.userName);
        audit.setUpdatedAt(LocalDateTime.now());
        audits.add(audit);
    }

    @PreUpdate
    public void onPreUpdate() {
        AuditInfoEntity audit = new AuditInfoEntity();
        audit.setUser(this);
        audit.setUpdatedBy(this.userName);
        audit.setUpdatedAt(LocalDateTime.now());
        audits.add(audit);
    }

    @PreRemove
    public void onPreRemove() {
        AuditInfoEntity audit = new AuditInfoEntity();
        audit.setUser(this);
        audit.setUpdatedBy(this.userName);
        audit.setUpdatedAt(LocalDateTime.now());
        audits.add(audit);
    }


}
