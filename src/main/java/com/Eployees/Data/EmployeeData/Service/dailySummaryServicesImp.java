package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.dailySummary;
import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Repository.dailySummaryRepository;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import org.springframework.stereotype.Service;

@Service
public class dailySummaryServicesImp implements dailySummaryService
{
    private final dailySummaryRepository SummaryRepository;
    private final employeeRepository employeeRepository;

    public dailySummaryServicesImp(dailySummaryRepository SummaryRepository, employeeRepository employeeRepository)
    {
        this.SummaryRepository = SummaryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public dailySummary saveSummary ( dailySummary summary)
    {
        if (summary.getEmployee().getEmpId() == 0)
        {
            throw new IllegalArgumentException(("Employee Id is required"));
        }
        long empId = summary.getEmployee().getEmpId();
        employee existing = employeeRepository.findById(empId).orElseThrow(() -> new IllegalArgumentException("Employee is not existing"));
        summary.setEmployee(existing);
        return SummaryRepository.save(summary);
    }

    @Override
    public dailySummary updateSummary (long empId, dailySummary summary)
    {
        if (summary.getEmployee().getEmpId() == 0)
        {
            throw new IllegalArgumentException("Employee Id is required ");
        }
        dailySummary existSummary = SummaryRepository.findById(empId).orElseThrow(()-> new IllegalArgumentException("Employee is not existing"));
        existSummary.setTotalCount(summary.getTotalCount()+existSummary.getDailyCount());
        return SummaryRepository.save(existSummary);
    }

    @Override
    public dailySummary getSummaryByEmpId(long empId)
    {
        return SummaryRepository.getReferenceById(empId);
    }
}
