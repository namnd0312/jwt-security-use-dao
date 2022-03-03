package com.namnd.springjwtdao.dao;

import com.namnd.springjwtdao.dto.UserDTO;

import java.io.Serializable;

public interface UserDao extends Serializable, BaseDAO{

    UserDTO findByUserName(String userName);
}
