package com.Employees.Data.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class changePassword {
    private String oldPassword;
    private String newPassword;
}
