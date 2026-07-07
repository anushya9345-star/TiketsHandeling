package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Entity.binEnum;
import com.Eployees.Data.EmployeeData.Entity.dailySummary;
import com.Eployees.Data.EmployeeData.Repository.binRepository;
import com.Eployees.Data.EmployeeData.Repository.dailySummaryRepository;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import com.Eployees.Data.EmployeeData.Service.dailySummaryService;
import com.Eployees.Data.EmployeeData.Specification.dailySummarySpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/dailysummary")
public class dailySummaryController
{
    private final dailySummaryService dailySummaryService;
    private final dailySummaryRepository dailySummaryRepository;
    private final employeeRepository employeeRepository;
    private final binRepository binRepository;
    public dailySummaryController (dailySummaryService dailySummaryService, dailySummaryRepository dailySummaryRepository, employeeRepository employeeRepository,binRepository binRepository)
    {
        this.dailySummaryService = dailySummaryService;
        this.dailySummaryRepository = dailySummaryRepository;
        this.employeeRepository = employeeRepository;
        this.binRepository = binRepository;
    }

    @PreAuthorize("hasAnyRole('Admin', 'Engineer')")
    @PostMapping("/create")
    public ResponseEntity<dailySummary> saveSummary (@RequestBody dailySummary summary)
    {
        return new ResponseEntity<>(dailySummaryService.saveSummary(summary), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('Admin','Engineer')")
    @PutMapping("/updateById/{empId}")
    public ResponseEntity<dailySummary> updateSummary (@PathVariable long empId, @RequestBody dailySummary summary)
    {
        return new ResponseEntity<>(dailySummaryService.updateSummary(empId, summary), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Engineer')")
    @GetMapping("/getById")
    public ResponseEntity<dailySummary> getSummaryByEmpId (@RequestParam Long empId)
    {
        return new ResponseEntity<>(dailySummaryService.getSummaryByEmpId(empId),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDailySummary (@RequestParam(required = false) String empName,
                                                 @RequestParam(required = false) String ecnNum,
                                                 @RequestParam(required = false) binEnum binName)
    {
        dailySummary summary = null;
        if (empName!= null && !empName.isBlank())
        {
            summary = dailySummaryRepository.findByEmployee(employeeRepository.findByEmpName(empName));
        }
        else if (ecnNum != null && !ecnNum.isBlank())
        {
            summary = dailySummaryRepository.findByEmployee(employeeRepository.findByEcnNum(ecnNum));
        }
        else if (binName != null)
        {
            summary = dailySummaryRepository.findByEmployee(employeeRepository.findBybin(binRepository.findBybinName(binName)));
        }

        if (empName == null && ecnNum == null && binName == null )
        {
            return ResponseEntity.badRequest().body("Please enter the correct parameter !!");
        }
        if (summary == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found !!");
        }
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/filter")
    public ResponseEntity <?> getSummaryByFilter(@RequestParam(required = false) String date,
                                                 @RequestParam(required = false) Integer belowCount,
                                                 @RequestParam(required = false) Integer aboveCount)
    {
        Specification <dailySummary> spec = ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
        if(date != null)
        {
            LocalDate updatedDate = LocalDate.parse(date);
            LocalDateTime start = updatedDate.atStartOfDay();
            LocalDateTime end = updatedDate.atTime(LocalTime.MAX);
            spec = spec.and(dailySummarySpecification.hasDate(start, end));
        }
        if(belowCount != null )
        {
            spec = spec.and(dailySummarySpecification.hasBelowDailyCount(belowCount));
        }
        if(aboveCount != null)
        {
            spec = spec.and(dailySummarySpecification.hasAboveDailySummary(aboveCount));
        }

        if(date == null && belowCount == null && aboveCount == null)
        {
            return ResponseEntity.badRequest().body("Enter the proper Parameter !!");
        }

        List<dailySummary> result = dailySummaryRepository.findAll(spec);

        if(result.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No date to show :(");
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/searchByDate/{date}")
    public ResponseEntity <?> getSummaryByDate (@PathVariable String date)
    {
        List <dailySummary> summary ;
        LocalDate summaryDate = LocalDate.parse(date);
        LocalDateTime start = summaryDate.atStartOfDay();
        LocalDateTime end = summaryDate.atTime(LocalTime.MAX);
        summary = dailySummaryRepository.findByDateBetween(start, end);
        return ResponseEntity.ok(summary);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteById")
    public void deleteSummaryById (@RequestParam long empId)
    {
        dailySummaryService.deleteSummaryById(empId);
    }

}