package com.namnd.springjwtdao.dao;

import com.namnd.springjwtdao.dto.UserDTO;

public interface UserDao {

    UserDTO findByUserName(String userName);
}
