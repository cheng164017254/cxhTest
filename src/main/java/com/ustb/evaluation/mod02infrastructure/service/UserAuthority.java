package com.ustb.evaluation.mod02infrastructure.service;

import com.ustb.evaluation.mod01common.domain.GrantedAuthorityImpl;
import com.ustb.evaluation.mod03system.domain.SysFunction.SysFunction;
import com.ustb.evaluation.mod03system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserAuthority {

    @Autowired
    SysUserServiceImpl service;

    public List<SimpleGrantedAuthority> getAuthorities(String username) {
        List<SysFunction> list = service.selectFunction(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

        if (list != null) {
            for (SysFunction sf : list) {
                authorities.add(new SimpleGrantedAuthority(sf.getCode()));
            }
        }
        return authorities;
    }

}
