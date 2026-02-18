package com.Eployees.Data.EmployeeData.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name ="Emp_Details")
@Data
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

    @JsonProperty("binName")
    public binEnum getBinName ()
    {
        return bin != null ? bin.getBinName() : null ;
    }



}
