package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.dailySummary;
import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Repository.dailySummaryRepository;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
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
        summary.setTotalCount(summary.getTotalCount()+summary.getDailyCount());
        return SummaryRepository.save(summary);
    }

    @Override
    public dailySummary updateSummary (long empId, dailySummary summary)
    {
        employee existing = employeeRepository.findById(empId).orElseThrow(() -> new IllegalArgumentException("Summary can't be update as summary with this Id doesn't exist"));
        dailySummary existSummary = SummaryRepository.findById(empId).orElseThrow(()-> new IllegalArgumentException("Summary can't be find"));
        existSummary.setEmployee(existing);
        existSummary.setDailyCount(summary.getDailyCount());
        existSummary.setTotalCount(existSummary.getTotalCount()+summary.getDailyCount());
        return SummaryRepository.save(existSummary);
    }

    @Override
    public dailySummary getSummaryByEmpId(long empId)
    {
        return SummaryRepository.findById(empId).orElseThrow(()->  new IllegalArgumentException("Enter the correct Employee Id to get Summary"));
    }

    @Override
    public void deleteSummaryById (long empId)
    {
        SummaryRepository.deleteById(empId);
    }

}
