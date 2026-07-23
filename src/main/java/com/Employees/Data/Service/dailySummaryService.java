package com.Employees.Data.Service;

import com.Employees.Data.Entity.dailySummary;

public interface dailySummaryService
{
    dailySummary saveSummary (dailySummary summary);
    dailySummary getSummaryByEmpId(long empId);
    dailySummary updateSummary (long empId, dailySummary summary);
    void deleteSummaryById (long empId);
}
