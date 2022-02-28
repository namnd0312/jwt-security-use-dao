package com.namnd.springjwtdao.dao.impl;

import com.namnd.springjwtdao.dao.UserDao;
import com.namnd.springjwtdao.dto.UserDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;

@Repository
public class UserDaoImpl extends AbstractBaseDAO implements UserDao {
    @Override
    public UserDTO findByUserName(String userName) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * ");
        sqlBuilder.append(" FROM users u ");
        sqlBuilder.append(" WHERE u.username = :user_name");
        Map<String, Object> parameter = Collections.singletonMap("user_name", userName);
        return getNamedParameterJdbcTemplate().queryForObject(sqlBuilder.toString(), parameter,
                BeanPropertyRowMapper.newInstance(UserDTO.class));
    }
}
