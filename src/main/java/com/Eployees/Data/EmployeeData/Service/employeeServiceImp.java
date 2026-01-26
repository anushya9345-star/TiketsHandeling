package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;

import org.springframework.stereotype.Service;

@Service
public class employeeServiceImp implements employeeService
{
    private final employeeRepository employeeRepository;

    public employeeServiceImp(employeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public employee saveEmployee(employee employee)
    {
        employee savedEmp = employeeRepository.save(employee);
        String ecnNum = "IBL" + (6000 + savedEmp.getEmpId());
        savedEmp.setecnNum(ecnNum);
        return employeeRepository.save(savedEmp);
    }

    @Override
    public employee getEmpByEmpId(long empId)
    {
        return employeeRepository.findById(empId).orElse(null);
    }

    @Override
    public employee getEmpByBinName(String binName)
    {
        return employeeRepository.findBybinName(binName);
    }

    @Override
    public void deleteEmployeeById ( long empId)
    {
        employeeRepository.deleteById(empId);
    }

    @Override
    public employee updateEmployeeName(Long emplId, employee employee)
    {
        employee existing = employeeRepository.findById(emplId).orElseThrow(()->new RuntimeException("Employee can't find"));
        existing.setEmpName(employee.getEmpName());
        existing.setBinName(employee.getBinName());
        return employeeRepository.save(existing);
    }
}
