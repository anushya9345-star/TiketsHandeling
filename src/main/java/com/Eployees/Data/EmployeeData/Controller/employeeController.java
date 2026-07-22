package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Dto.employeeDto;
import com.Eployees.Data.EmployeeData.Entity.binEnum;
import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Mappers.employeeMapping;
import com.Eployees.Data.EmployeeData.Repository.binRepository;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import com.Eployees.Data.EmployeeData.Service.employeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

//    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/updateById/{empId}")
    public ResponseEntity<employeeDto> updateEmployeeById(@PathVariable long empId, @RequestBody employee employee)
    {
        return new ResponseEntity<>(employeeService.updateEmployeeName(empId, employee),HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyRole('Admin', 'Engineer')")
    @GetMapping("/getById/{empId}")
    public ResponseEntity<employeeDto> getEmpById(@PathVariable long empId)
    {
        employee existingEmp = employeeRepository.findById(empId).orElseThrow(()-> new IllegalArgumentException("Employee can't be find"));
        return ResponseEntity.ok(employeeMapping.empToDo(existingEmp));
    }

//    @PreAuthorize("hasAnyRole('Admin','Engineer')")
    @GetMapping("/getByBinName")
    public ResponseEntity<employeeDto> getEmpByBin (@RequestParam binEnum binName )
    {
        employee existingEmp = employeeRepository.findBybin(binRepository.findBybinName(binName));
        return ResponseEntity.ok(employeeMapping.empToDo(existingEmp));
    }

//    @GetMapping("/empByPage")
    public ResponseEntity<Page<employee>> getEmployeeAsPage (@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam (defaultValue = "10") int size)
    {
        return ResponseEntity.ok(employeeService.getEmployeeAsPage(page, size));
    }

//    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteById/{empId}")
    public ResponseEntity<Void> deleteEmployeeById (@PathVariable long empId)
    {
        employeeService.deleteEmployeeById(empId);
        return ResponseEntity.ok().build();
    }

//    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/search")
    public ResponseEntity<?> searchEmployees (@RequestParam(required = false) String empName,
                                                   @RequestParam(required = false) String ecnNum,
                                                   @RequestParam(required = false) binEnum binName,
                                                   @RequestParam(required = false) String binId)
    {
        employee existingEmp = null;
        if(empName != null && !empName.isBlank())
        {
            existingEmp = employeeRepository.findByEmpName(empName);
        }
        else if (ecnNum != null && !ecnNum.isBlank())
        {
            existingEmp = employeeRepository.findByEcnNum(ecnNum);
        }
        else if (binName != null )
        {
            existingEmp = employeeRepository.findBybin(binRepository.findBybinName(binName));
        }
        else if (binId != null && !binId.isBlank())
        {
            existingEmp = employeeRepository.findBybin(binRepository.findById(binId).orElseThrow(() ->new IllegalArgumentException("Bin id is not existing :(")));
        }

        if (empName == null  && ecnNum == null  && binName == null && binId == null)
        {
            return ResponseEntity.badRequest().body("Enter the correct parameter!!!");
        }
        if (existingEmp != null) {
            return ResponseEntity.ok(employeeMapping.empToDo(existingEmp));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee can't be found :(");
        }
    }

    @GetMapping("/sorting")
    public ResponseEntity<Page<employee>> getEmployeesBySorting (@RequestParam(defaultValue = "empId") String sortBy,
                                                                 @RequestParam(defaultValue = "ASC") String direction,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size)
    {
        Sort sort = direction.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok().body(employeeRepository.findAll(pageable));

    }

}
