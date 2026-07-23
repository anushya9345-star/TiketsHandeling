package com.Employees.Data.Mappers;

import com.Employees.Data.Dto.binDto;
import com.Employees.Data.Entity.bin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface binMapping {

    @Mapping(source = "employee.empName", target = "empName")
    binDto binTodo (bin bin);
}
