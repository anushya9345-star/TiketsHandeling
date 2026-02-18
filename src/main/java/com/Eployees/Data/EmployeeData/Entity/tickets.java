package com.Eployees.Data.EmployeeData.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import jakarta.persistence.*;

@Entity
@Data
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
    @JsonFormat(pattern = "YYYY-MM-DD HH:MM")
    private LocalDateTime createdDate;

    @JsonProperty("UpdatedDate")
    @UpdateTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:MM")
    private LocalDateTime modifiedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private String notes;

    private String statusReason;

    @JsonProperty("binName")
    public binEnum getBinName ()
    {
        return bin != null ? bin.getBinName():null;
    }
}
