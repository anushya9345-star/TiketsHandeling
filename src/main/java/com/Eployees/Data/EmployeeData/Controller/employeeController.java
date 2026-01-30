package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import com.Eployees.Data.EmployeeData.Service.employeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
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
    public ResponseEntity<employee> saveEmployee (@RequestBody employee employee)
    {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<employee> updateEmployeeById(@PathVariable long empId, @RequestBody employee employee)
    {
        return new ResponseEntity<>(employeeService.updateEmployeeName(empId, employee),HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<employee> getEmpById(@PathVariable long empId)
    {
        return new ResponseEntity<>(employeeService.getEmpByEmpId(empId),HttpStatus.OK);
    }

    @GetMapping("/binName")
    public ResponseEntity<employee> getEmpByBin (@RequestParam String binName )
    {
        return new ResponseEntity<>(employeeService.getEmpByBinName(binName), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public void deleteEmployeeById (@PathVariable long empId)
    {
        employeeRepository.deleteById(empId);
    }

}
