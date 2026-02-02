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

    @PostMapping("/create")
    public ResponseEntity<employee> saveEmployee (@RequestBody employee employee)
    {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/updateById/{empId}")
    public ResponseEntity<employee> updateEmployeeById(@PathVariable long empId, @RequestBody employee employee)
    {
        return new ResponseEntity<>(employeeService.updateEmployeeName(empId, employee),HttpStatus.OK);
    }

    @GetMapping("/getById/{empId}")
    public ResponseEntity<employee> getEmpById(@PathVariable long empId)
    {
        return new ResponseEntity<>(employeeService.getEmpByEmpId(empId),HttpStatus.OK);
    }

    @GetMapping("/getByBinName")
    public ResponseEntity<employee> getEmpByBin (@RequestParam String binName )
    {
        return new ResponseEntity<>(employeeService.getEmpByBinName(binName), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{empId}")
    public ResponseEntity<Void> deleteEmployeeById (@PathVariable long empId)
    {
        employeeService.deleteEmployeeById(empId);
        return ResponseEntity.ok().build();
    }

}
