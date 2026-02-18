package com.Eployees.Data.EmployeeData.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class bin {

    @Id
    private String binId;

    @OneToMany(mappedBy = "bin", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<tickets> requestsList;

    @OneToOne(mappedBy = "bin", cascade = CascadeType.ALL)
    @JsonManagedReference
    private employee employee;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private binEnum binName;

    Boolean isActive;

    public bin()
    {}
    public bin(String binId, binEnum binName, Boolean isActive)
    {
        this.binId = binId;
        this.binName = binName;
        this.isActive = isActive;
    }

    @JsonProperty("EmpName")
    public String getEmpName ()
    {
        return employee != null ? employee.getEmpName():null;
    }
}
