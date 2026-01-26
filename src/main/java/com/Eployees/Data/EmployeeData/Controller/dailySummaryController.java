package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Entity.dailySummary;
import com.Eployees.Data.EmployeeData.Service.dailySummaryService;
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
    public dailySummary saveSummary ( @RequestBody dailySummary summary)
    {
        System.out.println(summary.getEmployee().getEmpId());
        return dailySummaryService.saveSummary( summary);
    }

    @PutMapping("/updateSummary/{empId}")
    public dailySummary updateSummary (@PathVariable long empId, @RequestBody dailySummary summary)
    {
        return dailySummaryService.updateSummary(empId, summary);
    }

    @GetMapping("/sumById")
    public dailySummary getSummaryByEmpId (@RequestParam Long empId)
    {
        return dailySummaryService.getSummaryByEmpId(empId);
    }

}