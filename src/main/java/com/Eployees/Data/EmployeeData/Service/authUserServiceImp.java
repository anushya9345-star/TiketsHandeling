package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.authUser;
import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Repository.authUserRepository;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class authUserServiceImp implements authUserService {

    private final authUserRepository authUserRepository;
    private final employeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public authUser createUser (authUser authUser)
    {
        employee existingEmployee = employeeRepository.findById(authUser.getEmployee().getEmpId()).orElseThrow(()->new IllegalArgumentException(authUser.getEmpId()+ " is Invalid User Id"));
        authUser.setEmployee(existingEmployee);
        String existingUser = existingEmployee.getEcnNum();
        authUser.setUserId((existingUser + "@org.com"));
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        return authUserRepository.save(authUser);
    }

    @Override
    public authUser getUser (long empId)
    {
        return authUserRepository.findById(empId).orElseThrow(()-> new IllegalArgumentException("Employee didn't register, Kindly Register to continue!"));
    }
}
