package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Entity.authUser;
import com.Eployees.Data.EmployeeData.Service.authUserService;
import com.Eployees.Data.EmployeeData.Repository.authUserRepository;
import com.Eployees.Data.EmployeeData.util.jwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authUserController {

    private final authUserService authUserService;
    private final authUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtUtil jwtUtil;


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
    public ResponseEntity<?> loginWithId (@RequestBody authUser user)
    {
        var existUser = authUserRepository.findByuserId(user.getUserId());
        if (existUser.isEmpty())
        {
            return new ResponseEntity<>("User Id is required !!", HttpStatus.UNAUTHORIZED);
        }
        authUser registeredUser =  existUser.get();
        if (! passwordEncoder.matches(user.getPassword(), registeredUser.getPassword()) )
        {
            return new ResponseEntity <> ("Invalid User!!", HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtil.generateToken(user.getUserId());
        return  ResponseEntity.ok(token);
    }

}
