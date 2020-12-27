package com.shawn.auth.dao;

import com.shawn.auth.bean.SysUser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class SysUserDao {

    private static BeanPropertyRowMapper<SysUser> ROW_MAPPER = new BeanPropertyRowMapper<>(SysUser.class);
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<SysUser> queryList() {
        return namedParameterJdbcTemplate.query("select * from sys_user", new HashMap<>(), ROW_MAPPER);
    }

    public SysUser queryUser(String username) {
        Map params = new HashMap<>();
        params.put("username", username);
        List<SysUser> list = namedParameterJdbcTemplate.query("select * from sys_user where username = :username", params, ROW_MAPPER);
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }


}
