package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import com.Eployees.Data.EmployeeData.Entity.bin;
import com.Eployees.Data.EmployeeData.Repository.binRepository;

import org.springframework.stereotype.Service;

@Service
public class employeeServiceImp implements employeeService
{
    private final employeeRepository employeeRepository;
    private final binRepository binRepository;

    public employeeServiceImp(employeeRepository employeeRepository, binRepository binRepository)
    {
        this.employeeRepository = employeeRepository;
        this.binRepository = binRepository;
    }

    @Override
    public employee saveEmployee(employee employee)
    {

        employee savedEmp = employeeRepository.save(employee);
        String ecnNum = "IBL" + (6000 + savedEmp.getEmpId());
        savedEmp.setEcnNum(ecnNum);
        String binId = employee.getBin().getBinId();
        bin assignedBin = binRepository.findById(binId).orElseThrow(()-> new IllegalArgumentException("Bin id is wrong"));
        assignedBin.setEmployee(savedEmp);
        assignedBin.setIsActive(true);
        binRepository.save(assignedBin);
        return employeeRepository.save(savedEmp);
    }

    @Override
    public employee updateEmployeeName(long emplId, employee employee)
    {
        employee existing = employeeRepository.findById(emplId).orElseThrow(()->new RuntimeException("Employee can't find"));
        existing.setEmpName(employee.getEmpName());
        existing.setBin(employee.getBin());
        return employeeRepository.save(existing);
    }

    @Override
    public employee getEmpByEmpId(long empId)
    {
        return employeeRepository.findById(empId).orElse(null);
    }

    @Override
    public employee getEmpByBinName
            (String binName )
    {
        bin existingBin = binRepository.findBybinName(binName);
        return employeeRepository.findBybin(existingBin);
    }

    @Override
    public void deleteEmployeeById ( long empId)
    {
        employee currentEmp = employeeRepository.findById(empId).orElseThrow(() -> new IllegalArgumentException("Employee doesn't exist"));
        bin AssignedBin = currentEmp.getBin();
        AssignedBin.setIsActive(false);
        employeeRepository.deleteById(empId);
    }

}
