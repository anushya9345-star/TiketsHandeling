package com.Employees.Data.Repository;

import com.Employees.Data.Entity.bin;
import com.Employees.Data.Entity.binEnum;
import com.Employees.Data.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface binRepository extends JpaRepository <bin, String>
{
    bin findBybinName (binEnum binName);
    bin findByEmployee (employee employee);
}
