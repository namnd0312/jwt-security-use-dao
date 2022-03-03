package com.namnd.springjwtdao.service.impl;

import com.namnd.springjwtdao.dao.RoleDao;
import com.namnd.springjwtdao.dao.UserDao;
import com.namnd.springjwtdao.dto.UserDTO;
import com.namnd.springjwtdao.model.Role;
import com.namnd.springjwtdao.model.User;
import com.namnd.springjwtdao.model.UserPrinciple;
import com.namnd.springjwtdao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public Long save(User user) {
        return (Long) userDao.save(user);
    }

    @Override
    public UserDTO findByUserName(String username) {
        UserDTO userDTO = this.userDao.findByUserName(username);

        if (userDTO != null) {
            List<Role> roles = roleDao.findAllRoleByUserId(userDTO.getId());
            userDTO.setRoles(roles);
        }

        return userDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = this.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return UserPrinciple.build(user);
    }
}
