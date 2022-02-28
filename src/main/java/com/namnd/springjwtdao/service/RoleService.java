package com.namnd.springjwtdao.service;

import com.namnd.springjwtdao.model.Role;

public interface RoleService {

    void save(Role role);

    Role findByName(String name);

    //Đẩy thay đổi vào DB để query lại
    void flush();
}
