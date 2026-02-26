package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Dto.employeeDto;
import com.Eployees.Data.EmployeeData.Entity.binEnum;
import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Mappers.employeeMapping;
import com.Eployees.Data.EmployeeData.Repository.binRepository;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import com.Eployees.Data.EmployeeData.Service.employeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class employeeController
{
    private final employeeService employeeService;
    private final employeeRepository employeeRepository;
    private final employeeMapping employeeMapping;
    private final binRepository binRepository;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/create")
    public ResponseEntity<employeeDto> saveEmployee (@RequestBody employee employee)
    {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/updateById/{empId}")
    public ResponseEntity<employeeDto> updateEmployeeById(@PathVariable long empId, @RequestBody employee employee)
    {
        return new ResponseEntity<>(employeeService.updateEmployeeName(empId, employee),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Engineer')")
    @GetMapping("/getById/{empId}")
    public ResponseEntity<employeeDto> getEmpById(@PathVariable long empId)
    {
        employee existingEmp = employeeRepository.findById(empId).orElseThrow(()-> new IllegalArgumentException("Employee can't be find"));
        return ResponseEntity.ok(employeeMapping.empToDo(existingEmp));
    }

    @PreAuthorize("hasAnyRole('Admin','Engineer')")
    @GetMapping("/getByBinName")
    public ResponseEntity<employeeDto> getEmpByBin (@RequestParam binEnum binName )
    {
        employee existingEmp = employeeRepository.findBybin(binRepository.findBybinName(binName));
        return ResponseEntity.ok(employeeMapping.empToDo(existingEmp));
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteById/{empId}")
    public ResponseEntity<Void> deleteEmployeeById (@PathVariable long empId)
    {
        employeeService.deleteEmployeeById(empId);
        return ResponseEntity.ok().build();
    }

}
