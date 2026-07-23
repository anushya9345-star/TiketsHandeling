package com.Employees.Data.Repository;

import com.Employees.Data.Entity.dailySummary;
import com.Employees.Data.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface dailySummaryRepository extends JpaRepository <dailySummary, Long>, JpaSpecificationExecutor<dailySummary>
{
    @Query(value = "SELECT COALESCE(SUM(daily_count), 0) FROM daily_summary", nativeQuery = true)
    long getTotalDailyCount();
    dailySummary findByEmployee(employee employee);
    List <dailySummary> findByDateBetween(LocalDateTime start, LocalDateTime end);
}