package com.Eployees.Data.EmployeeData.Repository;

import com.Eployees.Data.EmployeeData.Entity.dailySummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface dailySummaryRepository extends JpaRepository <dailySummary, Long>
{

}