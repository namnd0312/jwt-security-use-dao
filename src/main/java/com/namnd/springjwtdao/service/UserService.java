package com.namnd.springjwtdao.service;

import com.namnd.springjwtdao.dto.UserDTO;
import com.namnd.springjwtdao.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Long save(User user);

    UserDTO findByUserName(String username);
}
