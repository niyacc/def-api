package cn.niya.api.service;

import cn.niya.api.entity.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO user = new UserPO();
        user.setUserName("aaa");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setUserAuthorities("admin");
        return new User(user.getUserName(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getUserAuthorities()));
    }
}
