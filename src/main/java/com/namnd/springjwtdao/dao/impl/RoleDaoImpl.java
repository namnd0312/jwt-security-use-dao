package com.namnd.springjwtdao.dao.impl;

import com.namnd.springjwtdao.dao.RoleDao;
import com.namnd.springjwtdao.model.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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

    @Override
    public Optional<Role> findByName(String name) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * ");
        sqlBuilder.append(" FROM roles r ");
        sqlBuilder.append(" WHERE r.name = :role_name");
        Map<String, Object> parameter = Collections.singletonMap("role_name", name);

        return this.queryForObject(sqlBuilder.toString(), parameter,
                BeanPropertyRowMapper.newInstance(Role.class));
    }
}
