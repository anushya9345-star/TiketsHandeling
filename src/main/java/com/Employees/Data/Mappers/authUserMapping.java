package com.Employees.Data.Mappers;

import com.Employees.Data.Dto.authUserDto;
import com.Employees.Data.Entity.authUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface authUserMapping {
    authUserDto authUserDto (authUser user);
}
