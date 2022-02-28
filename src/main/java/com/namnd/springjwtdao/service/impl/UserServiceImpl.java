package com.namnd.springjwtdao.service.impl;

import com.namnd.springjwtdao.dao.RoleDao;
import com.namnd.springjwtdao.dao.UserDao;
import com.namnd.springjwtdao.dto.UserDTO;
import com.namnd.springjwtdao.model.Role;
import com.namnd.springjwtdao.model.User;
import com.namnd.springjwtdao.model.UserPrinciple;
import com.namnd.springjwtdao.repository.UserRepository;
import com.namnd.springjwtdao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public UserDTO findUserByUserName(String username) {
        UserDTO userDTO = this.userDao.findByUserName(username);

        if(userDTO != null){
            List<Role> roles = roleDao.findAllRoleByUserId(userDTO.getId());
            userDTO.setRoles(roles);
        }

        return userDTO;
    }

    @Override
    public Boolean existsByUsername(String userName) {
        return userRepository.existsByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);

        if(!user.isPresent()){
            throw new UsernameNotFoundException(username);
        }

        return UserPrinciple.build(user.get());
    }
}
