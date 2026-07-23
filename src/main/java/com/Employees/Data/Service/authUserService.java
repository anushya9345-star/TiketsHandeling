package com.Employees.Data.Service;


import com.Employees.Data.Dto.changePassword;
import com.Employees.Data.Entity.authUser;

public interface authUserService {

    authUser createUser (authUser authUser);
    authUser getUser (long empId);
    String loginWithId (authUser user);
    authUser changePassword (long empId, changePassword user);
    authUser changeRole (long empId, authUser user);
}
