package com.namnd.springjwtdao.controller;

import com.namnd.springjwtdao.dto.JwtResponseDto;
import com.namnd.springjwtdao.dto.RegisterDto;
import com.namnd.springjwtdao.dto.UserDTO;
import com.namnd.springjwtdao.dto.mapper.RegisterDtoMapper;
import com.namnd.springjwtdao.model.ERole;
import com.namnd.springjwtdao.model.User;
import com.namnd.springjwtdao.model.UserRole;
import com.namnd.springjwtdao.service.JwtService;
import com.namnd.springjwtdao.service.RoleService;
import com.namnd.springjwtdao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RegisterDtoMapper registerDtoMapper;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDTO currentUser = userService.findByUserName(user.getUsername());
        return ResponseEntity.ok(new JwtResponseDto(currentUser.getId(), jwt, userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {

        UserDTO user = this.userService.findByUserName(registerDto.getUsername());

        if(user != null) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        User entity = registerDtoMapper.toEntity(registerDto);
        Long userId = userService.save(entity);

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(ERole.ROLE_USER.getValue());
        roleService.saveUserRole(userRole);

        return ResponseEntity.ok().body("User registered successfully!");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam String userName) {

        UserDTO userDTO = this.userService.findByUserName(userName);

        return ResponseEntity.ok().body(userDTO);
    }
}