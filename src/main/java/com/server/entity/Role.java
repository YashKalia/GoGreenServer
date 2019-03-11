package com.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private long id;
    private String role;

    public Role(){}

    public Role(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Role)) {
            return false;
        }
        Role other = (Role) obj;
        if (id != other.id) {
            return false;
        }
        if (!role.equals(other.role)) {
            return false;
        }
        return true;
    }

}
