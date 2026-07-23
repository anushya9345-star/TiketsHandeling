package com.Employees.Data.Service;

import com.Employees.Data.Dto.changePassword;
import com.Employees.Data.Entity.authUser;
import com.Employees.Data.Entity.employee;
import com.Employees.Data.Repository.authUserRepository;
import com.Employees.Data.Repository.employeeRepository;
import com.Employees.Data.util.jwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class authUserServiceImp implements authUserService, UserDetailsService {

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
        if (! passwordEncoder.matches(user.getPassword(), existingUser.getPassword()))
        {
            throw new SecurityException("Invalid Password !!");
        }
        String token = jwtUtil.generateToken(user.getUserId(),existingUser.getRole());
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

    @Override
    public authUser changeRole (long empId, authUser user)
    {
        authUser existingUser = authUserRepository.findById(empId).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        existingUser.setRole(user.getRole());
        return authUserRepository.save(existingUser);
     }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authUserRepository.findByuserId(username).orElseThrow(()->new UsernameNotFoundException("User Not Found:"+ username));
    }

}
