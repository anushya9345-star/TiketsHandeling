package com.Eployees.Data.EmployeeData.Repository;

import com.Eployees.Data.EmployeeData.Entity.bin;
import com.Eployees.Data.EmployeeData.Entity.binEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface binRepository extends JpaRepository <bin, String>
{
    bin findBybinName (binEnum binName);
}
