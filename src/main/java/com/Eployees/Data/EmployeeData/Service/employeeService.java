package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Dto.employeeDto;
import com.Eployees.Data.EmployeeData.Entity.binEnum;
import com.Eployees.Data.EmployeeData.Entity.employee;
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

