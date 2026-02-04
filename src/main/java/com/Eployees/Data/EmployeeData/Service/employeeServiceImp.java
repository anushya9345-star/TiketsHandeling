package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.binEnum;
import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import com.Eployees.Data.EmployeeData.Entity.bin;
import com.Eployees.Data.EmployeeData.Repository.binRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
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
        if (employee.getEmpName() != null)
        {
            existing.setEmpName(employee.getEmpName());
        }
        if (employee.getBin() != null)
        {
            existing.getBin().setEmployee(null);
            bin toBechangeBin = binRepository.findById(employee.getBin().getBinId()).orElseThrow(()-> new IllegalArgumentException("Bin cam't be find"));
            existing.setBin(toBechangeBin);
            toBechangeBin.setEmployee(existing);
        }
        return employeeRepository.save(existing);
    }

    @Override
    public employee getEmpByEmpId(long empId)
    {
        return employeeRepository.findById(empId).orElseThrow(()-> new IllegalArgumentException("Employee Id miss match"));
    }

    @Override
    public employee getEmpByBinName
            (binEnum binName )
    {
        bin existingBin = binRepository.findBybinName(binName);
        if (existingBin == null)
        {
            throw new IllegalArgumentException("Invalid BinName Kindly check it once");
        }
        else {
            return employeeRepository.findBybin(existingBin);
        }
    }

    @Override
    public void deleteEmployeeById (long empId)
    {
        employee currentEmp = employeeRepository.findById(empId).orElseThrow(() -> new IllegalArgumentException("Employee doesn't exist"));
        bin AssignedBin = currentEmp.getBin();
        if(AssignedBin != null)
        {
            AssignedBin.setEmployee(null);
            AssignedBin.setIsActive(false);
            currentEmp.setBin(null);
        }
        employeeRepository.delete(currentEmp);
    }

}
