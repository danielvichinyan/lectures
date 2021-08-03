package com.knowit.lectures.repository;

import com.knowit.lectures.domain.entities.ERole;
import com.knowit.lectures.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(ERole name);
}

