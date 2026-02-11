package com.Eployees.Data.EmployeeData.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="Emp_Details")
@JsonIgnoreProperties({"DailySummary","empid"})
public class employee
{

    @OneToOne
    @JsonManagedReference
    private authUser authUser;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private dailySummary dailySummary;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "binId")
    private bin bin;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long empId  ;

    @JsonProperty("ecnNum")
    @Column(name = "ECN_number", unique = true)
    private String ecnNum;
    private String empName;
    public employee()
    {}

    public employee(bin bin, String empName)
    {
        this.bin = bin;
        this.empName = empName;
    }

    public String getEmpName()
    {
        return empName;
    }
    public void setEmpName(String Emp_Name)
    {
        this.empName = Emp_Name;
    }

    public String getEcnNum ()
    {
        return ecnNum;
    }
    public void setEcnNum(String ecnNum)
    {
        this.ecnNum = ecnNum;
    }

    public long getEmpId()
    {
        return empId;
    }
    public void setEmpId(long empId)
    {
        this.empId = empId;
    }

    public bin getBin ()
    {
        return bin;
    }
    public void setBin (bin bin)
    {
        this.bin = bin;
    }

    public dailySummary getSummary ()
    {
        return dailySummary;
    }
    public void setDailySummary(dailySummary dailySummary)
    {
        this.dailySummary = dailySummary;
    }

    public authUser getAuthUser ()
    {
        return authUser;
    }
    public void setAuthUser(authUser authUser)
    {
        this.authUser = authUser;
    }
    @JsonProperty("binName")
    public binEnum getBinName ()
    {
        return bin != null ? bin.getBinName() : null ;
    }



}
