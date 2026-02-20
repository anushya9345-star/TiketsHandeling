package com.Eployees.Data.EmployeeData.Service;


import com.Eployees.Data.EmployeeData.Dto.changePassword;
import com.Eployees.Data.EmployeeData.Entity.authUser;
import com.Eployees.Data.EmployeeData.Dto.authUserDto;

public interface authUserService {

    authUser createUser (authUser authUser);
    authUser getUser (long empId);
    String loginWithId (authUser user);
    authUser changePassword (long empId, changePassword user);
}
