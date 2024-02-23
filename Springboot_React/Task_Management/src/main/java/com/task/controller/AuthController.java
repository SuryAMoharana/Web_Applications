package com.task.controller;

import com.task.config.JwtProvider;
import com.task.model.User;
import com.task.repositories.UserRepository;
import com.task.request.LoginRequest;
import com.task.response.AuthResponse;
import com.task.services.CustomUserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserServiceImplementation customUserServiceImplementation;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{
        String email=user.getEmail();
        String password=user.getPassword();
        String fullName=user.getFullName();
        String role=user.getRole();

        User isEmailExist=userRepository.findByEmail(email);

        if(isEmailExist!=null){
            throw new Exception("Email is already used with another account");
        }

        //create new user
        User createdUser=new User();
        createdUser.setEmail(email);
        createdUser.setRole(role);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullName(fullName);

        User savedUser=userRepository.save(createdUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token= JwtProvider.generatedToken(authentication);
        AuthResponse response=new AuthResponse();
        response.setJwt(token);
        response.setMessage("Register Success");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){

        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();

        System.out.println(username+"----------"+password);

        Authentication authentication=authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=JwtProvider.generatedToken(authentication);
        AuthResponse authResponse=new AuthResponse();

        authResponse.setMessage("Login Success");
        authResponse.setJwt(token);
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse,HttpStatus.OK);

    }

    private Authentication authenticate(String username, String password){
        UserDetails userDetails=customUserServiceImplementation.loadUserByUsername(username);
        System.out.println("Sign in user details - "+userDetails);
        if(userDetails==null){
            System.out.println("Sign in user details - null "+userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            System.out.println("Sign in user details - password not match "+userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
