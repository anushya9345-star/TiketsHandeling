package com.Eployees.Data.EmployeeData.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class authUser
{

    @OneToOne
    @MapsId
    @JoinColumn(name ="empId")
    @JsonBackReference
    private employee employee;

    @Id
    private long empId;

    private String userId;
    private String password;
}
