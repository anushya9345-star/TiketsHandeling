package com.Eployees.Data.EmployeeData.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table (name = "Ticket_Details")

public class tickets
{

    @ManyToOne
    @JoinColumn(name = "binId")
    @JsonBackReference
    private bin bin;


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long requestId;

    @JsonProperty("requestCode")
    @Column(unique = true, nullable = false)
    private String requestCode;

    @JsonProperty("CreateDate")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @JsonProperty("UpdatedDate")
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private String notes;

    private String statusReason;

    private String assignedBin;

    public tickets()
    {}

    public tickets (StatusEnum Status, String assignedBin, String pendingReason)
    {
        this.status = Status;
        this.assignedBin = assignedBin;
        this.statusReason = pendingReason;
    }

    public long getRequestId()
    {
        return requestId;
    }
    public void setRequestId(long requestId)
    {
        this.requestId = requestId;
    }

    public String getRequestCode()
    {
        return requestCode;
    }
    public void setRequestCode(String requestCode)
    {
        this.requestCode = requestCode;
    }

    public StatusEnum getStatus()
    {
        return status;
    }
    public void setStatus(StatusEnum status)
    {
        this.status = status;
    }

    public String getStatusReason()
    {
        return statusReason;
    }
    public void setStatusReason(String statusReason)
    {
        this.statusReason = statusReason;
    }

    public String getNotes ()
    {
        return notes;
    }
    public void setNotes( String notes)
    {
        this.notes = notes;
    }

    public String getBinName()
    {
        return assignedBin;
    }
    public void setBinName(String assignedBin)
    {
        this.assignedBin = assignedBin;
    }

    public bin getBin ()
    {
        return bin;
    }
    public void setBin (bin bin)
    {
        this.bin = bin;
    }
}
