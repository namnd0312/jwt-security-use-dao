package com.namnd.springjwtdao.dao;

import com.namnd.springjwtdao.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {

    List<Role> findAllRoleByUserId(Long userId);
}
