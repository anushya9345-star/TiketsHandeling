package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.dailySummary;

public interface dailySummaryService
{
    dailySummary saveSummary (dailySummary summary);
    dailySummary getSummaryByEmpId(long empId);
    dailySummary updateSummary (long empId, dailySummary summary);
}
