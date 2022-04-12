package com.example.oraclehibernate.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="ALL_USERS")
public class User implements Serializable {
    @Column(name = "USER_ID")
    private int id;

    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CREATED")
    private Date created;

    @ManyToMany
    @JoinTable(
            name = "DBA_ROLE_PRIVS",
            joinColumns = {
                    @JoinColumn(name = "GRANTEE")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "GRANTED_ROLE")
            }

    )
    private Set<Role> roles;
    public User(){
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
