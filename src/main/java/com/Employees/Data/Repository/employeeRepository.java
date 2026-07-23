package com.Employees.Data.Repository;

import com.Employees.Data.Entity.bin;
import com.Employees.Data.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;



public interface employeeRepository extends JpaRepository<employee, Long>
{
    employee findBybin(bin bin);
    employee findByEmpName (String empName);
    employee findByEcnNum (String ecnNum);
    void delete(employee employee);
}
