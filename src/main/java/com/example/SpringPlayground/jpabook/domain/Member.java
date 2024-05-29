package com.example.SpringPlayground.jpabook.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="MEMBERS_JPA_BOOK")
public class Member {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME", nullable = false, length = 10)
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

enum RoleType{
    ADMIN, USER
}