package com.Employees.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Employees.Data.Entity.authUser;

import java.util.Optional;

public interface authUserRepository extends JpaRepository <authUser, Long>
{
    Optional <authUser> findByuserId(String userId);
}
