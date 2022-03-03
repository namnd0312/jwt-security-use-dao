package com.namnd.springjwtdao.dao;

import com.namnd.springjwtdao.model.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface RoleDao extends Serializable, BaseDAO {

    List<Role> findAllRoleByUserId(Long userId);

    Optional<Role> findByName(String name);
}
