package com.namnd.springjwtdao.service.impl;

import com.namnd.springjwtdao.dao.RoleDao;
import com.namnd.springjwtdao.model.UserRole;
import com.namnd.springjwtdao.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void saveUserRole(UserRole userRole) {
        roleDao.save(userRole);
    }
}
