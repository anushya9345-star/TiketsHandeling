package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Dto.changePassword;
import com.Eployees.Data.EmployeeData.Entity.authUser;
import com.Eployees.Data.EmployeeData.Service.authUserService;
import com.Eployees.Data.EmployeeData.Repository.authUserRepository;
import com.Eployees.Data.EmployeeData.util.jwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authUserController {

    private final authUserService authUserService;
    private final authUserRepository authUserRepository;

    @PostMapping("/registUser")
    public ResponseEntity<?> registerUser (@RequestBody authUser user)
    {
        Optional<authUser> existUser = authUserRepository.findByuserId(user.getUserId());
        if (existUser.isPresent())
        {
            return new ResponseEntity<>("User is already Registered!!", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(authUserService.createUser(user),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginWithId (@RequestBody authUser user)
    {
      return new ResponseEntity<>(authUserService.loginWithId(user), HttpStatus.ACCEPTED);
    }

    @PostMapping("/changePassword/{empId}")
    public ResponseEntity<?> changePassword (@PathVariable long empId, @RequestBody changePassword user)
    {
        return new ResponseEntity<>(authUserService.changePassword(empId, user),HttpStatus.OK);
    }
}
