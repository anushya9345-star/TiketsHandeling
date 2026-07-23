package com.Employees.Data.Mappers;

import com.Employees.Data.Dto.employeeDto;
import com.Employees.Data.Entity.employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface employeeMapping {
    employeeDto empToDo (employee employee);
}
