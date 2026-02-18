package com.Eployees.Data.EmployeeData.Mappers;

import com.Eployees.Data.EmployeeData.Dto.binDto;
import com.Eployees.Data.EmployeeData.Entity.bin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface binMapping {

    @Mapping(source = "employee.empName", target = "empName")
    binDto binTodo (bin bin);
}
