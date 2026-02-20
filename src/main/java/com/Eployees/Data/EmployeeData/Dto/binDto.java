package com.Eployees.Data.EmployeeData.Dto;

import com.Eployees.Data.EmployeeData.Entity.binEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class binDto {
    private String binId;
    private binEnum binName ;
    private String empName;
    private boolean isActive;

}
