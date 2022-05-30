package cn.niya.api.service;

import cn.niya.api.dao.UserDAO;
import cn.niya.api.entity.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @date 2020/05/30
 * user information services
 * do user information changed
 * example: change password , register and so on
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Object register(UserPO user) {
        user.setUserName("niya");
        user.setPassword(passwordEncoder.encode("0000.."));
        user.setUserAuthorities("user");
        user.setUserEmail("niya@niya.com");
        user.setUserAddress("1001");
        int insert = userDAO.insert(user);
        System.out.println(insert);
        return null;
    }

}
