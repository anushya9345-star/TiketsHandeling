package com.Eployees.Data.EmployeeData.Repository;

import com.Eployees.Data.EmployeeData.Entity.dailySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder;

public interface dailySummaryRepository extends JpaRepository <dailySummary, Long>
{
    @Query(value = "SELECT COALESCE(SUM(daily_count), 0) FROM daily_summary", nativeQuery = true)
    long getTotalDailyCount();
}