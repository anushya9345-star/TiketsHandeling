package com.Eployees.Data.EmployeeData.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Eployees.Data.EmployeeData.Entity.authUser;

import java.util.Optional;

public interface authUserRepository extends JpaRepository <authUser, Long>
{
    Optional <authUser> findByuserId(String userId);
}
