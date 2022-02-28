package com.namnd.springjwtdao.dto;

import com.namnd.springjwtdao.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String fullName;

    private List<Role> roles;
}
