package com.Eployees.Data.EmployeeData.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class bin {

    @Id
    private String binId;

    @OneToMany(mappedBy = "bin", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<tickets> requestsList;

    @OneToOne(mappedBy = "bin", cascade = CascadeType.ALL)
    @JsonManagedReference
    private employee employee;

    private String binName;
    Boolean isActive;

    public bin()
    {}
    public bin(String binId, String binName, Boolean isActive)
    {
        this.binId = binId;
        this.binName = binName;
        this.isActive = isActive;
    }

    public String getBinId()
    {
        return binId;
    }
    public void setBinId(String binId)
    {
        this.binId = binId;
    }

    public String getBinName ()
    {
        return binName;
    }
    public void setBinName(String binName)
    {
        this.binName = binName;
    }

    public Boolean getIsActive ()
    {
        return isActive;
    }
    public void setIsActive(Boolean isActive)
    {
        this.isActive = isActive;
    }

    public employee getEmployee()
    {
        return employee;
    }
    public void setEmployee( employee employee)
    {
        this.employee = employee;
    }

    public List <tickets> getRequestsList ()
    {
        return requestsList;
    }
    public void setRequestsList (List <tickets> requestsList)
    {
        this.requestsList = requestsList;
    }
}
