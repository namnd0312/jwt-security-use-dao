package com.namnd.springjwtdao.service;

import com.namnd.springjwtdao.dto.UserDTO;
import com.namnd.springjwtdao.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    void save(User user);

    Optional<User> findByUserName(String userName);

    UserDTO findUserByUserName(String username);

    Boolean existsByUsername(String userName);
}
