package com.knowit.lectures.domain.entities;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private ERole name;

    private Set<User> users;

    @Enumerated(EnumType.STRING)
    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles", cascade = {PERSIST, MERGE})
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

