package com.Eployees.Data.EmployeeData.Repository;

import com.Eployees.Data.EmployeeData.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;



public interface employeeRepository extends JpaRepository<employee, Long>
{

    employee findBybinName(String Bin_Name);
}
