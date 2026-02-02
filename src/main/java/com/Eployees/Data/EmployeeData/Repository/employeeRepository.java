package com.Eployees.Data.EmployeeData.Repository;

import com.Eployees.Data.EmployeeData.Entity.bin;
import com.Eployees.Data.EmployeeData.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;



public interface employeeRepository extends JpaRepository<employee, Long>
{
    employee findBybin(bin bin);
    void delete(employee employee);
}
