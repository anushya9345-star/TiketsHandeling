package com.Eployees.Data.EmployeeData.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class changePassword {
    private String oldPassword;
    private String newPassword;
}
