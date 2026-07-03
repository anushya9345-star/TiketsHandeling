package com.Eployees.Data.EmployeeData.Repository;

import com.Eployees.Data.EmployeeData.Entity.bin;
import com.Eployees.Data.EmployeeData.Entity.binEnum;
import com.Eployees.Data.EmployeeData.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface binRepository extends JpaRepository <bin, String>
{
    bin findBybinName (binEnum binName);
    bin findByEmployee (employee employee);
}
