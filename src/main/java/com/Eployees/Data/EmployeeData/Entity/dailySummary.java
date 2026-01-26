package com.Eployees.Data.EmployeeData.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class dailySummary
{
    @OneToOne
    @MapsId
    @JoinColumn(name = "empId")
    @JsonBackReference
    private employee employee;

    @Id
    private long empId;

    @UpdateTimestamp
    private LocalDateTime date;

    private int dailyCount;
    private int totalCount;

    public dailySummary(){}

    public dailySummary(employee employee, int dailyCount)
    {
        this.employee = employee;
        this.dailyCount = dailyCount;
    }

    public employee getEmployee()
    {
        return employee;
    }
    public void setEmployee(employee employee)
    {
        this.employee = employee;
    }

    public long getEmpId ()
    {
        return empId;
    }
    public void setEmpId (long empId)
    {
        this.empId = empId;
    }

    public int getDailyCount()
    {
        return dailyCount;
    }
    public void setDailyCount(int dailyCount)
    {
        this.dailyCount = dailyCount;
    }

    public int getTotalCount()
    {
        return totalCount;
    }
    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

}
