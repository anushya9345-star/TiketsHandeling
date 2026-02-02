package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.employee;

public interface employeeService
{
    employee saveEmployee(employee employee);
    employee updateEmployeeName(long emplId, employee employee);
    employee getEmpByEmpId(long empId);
    employee getEmpByBinName(String binName);
    void deleteEmployeeById(long empId);
}

