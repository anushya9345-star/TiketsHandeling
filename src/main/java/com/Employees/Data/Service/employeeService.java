package com.Employees.Data.Service;

import com.Employees.Data.Dto.employeeDto;
import com.Employees.Data.Entity.binEnum;
import com.Employees.Data.Entity.employee;
import org.springframework.data.domain.Page;

public interface employeeService
{
    employeeDto saveEmployee(employee employee);
    employeeDto updateEmployeeName(long emplId, employee employee);
    employee getEmpByEmpId(long empId);
    employee getEmpByBinName(binEnum binName);
    Page<employee> getEmployeeAsPage(int page, int size);
    void deleteEmployeeById(long empId);
}

