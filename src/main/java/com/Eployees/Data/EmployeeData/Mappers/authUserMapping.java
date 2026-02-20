package com.Eployees.Data.EmployeeData.Mappers;

import com.Eployees.Data.EmployeeData.Dto.authUserDto;
import com.Eployees.Data.EmployeeData.Entity.authUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface authUserMapping {
    authUserDto authUserDto (authUser user);
}
