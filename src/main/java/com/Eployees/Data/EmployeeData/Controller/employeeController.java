package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import com.Eployees.Data.EmployeeData.Service.employeeService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Employee")
public class employeeController
{
    private final employeeService employeeService;
    private final employeeRepository employeeRepository;

    public employeeController(employeeService employeeService, employeeRepository employeeRepository)
    {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/save")
    public employee saveEmployee (@RequestBody employee employee)
    {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{empId}")
    public employee getEmpById(@PathVariable long empId)
    {
        return employeeService.getEmpByEmpId(empId);
    }

    @GetMapping("/BinName")
    public employee getEmpByBinName (@RequestParam String binName)
    {
        return employeeService.getEmpByBinName(binName);
    }

    @DeleteMapping("/delete/{empId}")
    public void deleteEmployeeById (@PathVariable long empId)
    {
        employeeRepository.deleteById(empId);
    }

    @PutMapping("/update/{empId}")
    public employee updateEmployeeById(@PathVariable long empId, @RequestBody employee employee)
    {
        return employeeService.updateEmployeeName(empId, employee);
    }

}
