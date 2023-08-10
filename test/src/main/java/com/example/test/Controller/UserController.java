package com.example.test.Controller;

import com.example.test.Config.JwtTokenUtil;
import com.example.test.Dto.JwtResponse;
import com.example.test.Dto.LoginDto;
import com.example.test.Dto.RegisterDto;
import com.example.test.Model.User;
import com.example.test.Service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    JwtUserDetailService jwtUserDetailService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto register) {
        User newUser = new User();
        newUser.setName(register.getName());
        newUser.setEmail(register.getEmail());
        newUser.setPassword(register.getPassword());

        jwtUserDetailService.save(newUser);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto login) throws Exception {
        authenticate(login.getEmail(), login.getPassword());

        final UserDetails userDetails = jwtUserDetailService.loadUserByUsername(login.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    public void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED",e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

