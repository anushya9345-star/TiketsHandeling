package com.Eployees.Data.EmployeeData.Service;


import com.Eployees.Data.EmployeeData.Entity.authUser;

public interface authUserService {

    authUser createUser (authUser authUser);
    authUser getUser (long empId);
}
