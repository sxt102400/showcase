package com.shawn.auth.service;

import com.shawn.auth.bean.SysUser;
import com.shawn.auth.dao.SysUserDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 鉴权工具类：获取用户以及权限信息
 *
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //获取用户属性
        SysUser sysUser = sysUserDao.queryUser(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        //获取用户权限
        List<GrantedAuthority> authorities = new ArrayList<>();

        //返回用户权限信息
        User userDetails = new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
        return userDetails;
    }
}
