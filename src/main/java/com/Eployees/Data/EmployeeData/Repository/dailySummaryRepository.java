package com.Eployees.Data.EmployeeData.Repository;

import com.Eployees.Data.EmployeeData.Entity.dailySummary;
import com.Eployees.Data.EmployeeData.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder;

import java.time.LocalDateTime;
import java.util.List;

public interface dailySummaryRepository extends JpaRepository <dailySummary, Long>, JpaSpecificationExecutor<dailySummary>
{
    @Query(value = "SELECT COALESCE(SUM(daily_count), 0) FROM daily_summary", nativeQuery = true)
    long getTotalDailyCount();
    dailySummary findByEmployee(employee employee);
    List <dailySummary> findByDateBetween(LocalDateTime start, LocalDateTime end);
}