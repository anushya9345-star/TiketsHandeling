package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Entity.dailySummary;
import com.Eployees.Data.EmployeeData.Service.dailySummaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dailysummary")
public class dailySummaryController
{
    private final dailySummaryService dailySummaryService;
    public dailySummaryController (dailySummaryService dailySummaryService)
    {
        this.dailySummaryService = dailySummaryService;
    }

    @PostMapping("/count")
    public ResponseEntity<dailySummary> saveSummary (@RequestBody dailySummary summary)
    {
        return new ResponseEntity<>(dailySummaryService.saveSummary(summary), HttpStatus.CREATED);
    }

    @PutMapping("/updateSummary/{empId}")
    public ResponseEntity<dailySummary> updateSummary (@PathVariable long empId, @RequestBody dailySummary summary)
    {
        return new ResponseEntity<>(dailySummaryService.updateSummary(empId, summary), HttpStatus.OK);
    }

    @GetMapping("/sumById")
    public ResponseEntity<dailySummary> getSummaryByEmpId (@RequestParam Long empId)
    {
        return new ResponseEntity<>(dailySummaryService.getSummaryByEmpId(empId),HttpStatus.OK);
    }

}