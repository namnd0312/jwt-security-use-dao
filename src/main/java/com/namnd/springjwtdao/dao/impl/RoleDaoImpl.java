package com.namnd.springjwtdao.dao.impl;

import com.namnd.springjwtdao.dao.RoleDao;
import com.namnd.springjwtdao.model.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@Repository
public class RoleDaoImpl extends AbstractBaseDAO implements RoleDao {
    @Override
    public List<Role> findAllRoleByUserId(Long userId) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select ");
        sqlBuilder.append("r.id, ");
        sqlBuilder.append("r.name ");
        sqlBuilder.append("from user_roles ur ");
        sqlBuilder.append("join  roles r on ur.role_id = r.id ");
        sqlBuilder.append("where ur.user_id = :user_id");
        Map<String, Object> parameters = Collections.singletonMap("user_id", userId);

        return getNamedParameterJdbcTemplate().query(sqlBuilder.toString(), parameters,
                BeanPropertyRowMapper.newInstance(Role.class));

    }
}
