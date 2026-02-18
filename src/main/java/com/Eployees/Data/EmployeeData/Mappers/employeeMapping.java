package com.Eployees.Data.EmployeeData.Mappers;

import com.Eployees.Data.EmployeeData.Dto.employeeDto;
import com.Eployees.Data.EmployeeData.Entity.employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface employeeMapping {
    employeeDto empToDo (employee employee);
}
