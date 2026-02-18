package com.Eployees.Data.EmployeeData.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
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

    @JsonProperty("Employee")
    public String getEmpName ()
    {
        return employee != null ? employee.getEmpName():null;
    }

}
