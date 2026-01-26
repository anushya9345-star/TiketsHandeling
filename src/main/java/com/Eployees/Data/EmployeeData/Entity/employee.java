package com.Eployees.Data.EmployeeData.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@jakarta.persistence.Entity
@Table(name ="Emp_Details")
@JsonIgnoreProperties({"Request_List", "dailySummary"})
public class employee
{
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<tickets> requestsList;

    @OneToOne(mappedBy = "employee")
    @JsonManagedReference
    private dailySummary DailySummary;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long empId  ;

    @JsonProperty("ecnNum")
    @Column(name = "ECN_number", unique = true)
    private String ecnNum;
    private String empName;
    private String binName;
    public employee(){

    }
    public employee(String empName, String binName)
    {
        this.empName = empName;
        this.binName = binName;
    }

    public String getEmpName()
    {
        return empName;
    }
    public void setEmpName(String Emp_Name)
    {
        this.empName = Emp_Name;
    }

    public String getecnNum ()
    {
        return ecnNum;
    }
    public void setecnNum(String ecnNum)
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

    public String getBinName()
    {
        return binName;
    }
    public void setBinName(String Bin_Name)
    {
        this.binName = Bin_Name;
    }

}
