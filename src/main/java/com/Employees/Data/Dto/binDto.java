package com.Employees.Data.Dto;

import com.Employees.Data.Entity.binEnum;
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
