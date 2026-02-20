package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Dto.authUserDto;
import com.Eployees.Data.EmployeeData.Dto.changePassword;
import com.Eployees.Data.EmployeeData.Entity.authUser;
import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Repository.authUserRepository;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import com.Eployees.Data.EmployeeData.util.jwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class authUserServiceImp implements authUserService {

    private final authUserRepository authUserRepository;
    private final employeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtUtil jwtUtil;

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

    @Override
    public String loginWithId (authUser user)
    {
        authUser existingUser = authUserRepository.findByuserId(user.getUserId()).orElseThrow(()->new IllegalArgumentException("Invalid Id !!"));
        System.out.println(user.getPassword());
        if (! passwordEncoder.matches(user.getPassword(), existingUser.getPassword()))
        {
            throw new SecurityException("Invalid Password !!");
        }
        String token = jwtUtil.generateToken(user.getUserId());
        return token;
    }

    @Override
    public authUser changePassword (long empId, changePassword user)
    {
        authUser existingUser = authUserRepository.findById(empId).orElseThrow(()->new IllegalArgumentException("Invalid Id !!"));
        if (!passwordEncoder.matches((user.getOldPassword()),existingUser.getPassword()))
        {
            throw  new IllegalArgumentException("Invalid Password !!");
        }
        existingUser.setPassword(passwordEncoder.encode(user.getNewPassword()));
        return authUserRepository.save(existingUser);
    }
}
