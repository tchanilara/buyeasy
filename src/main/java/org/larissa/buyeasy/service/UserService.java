package org.larissa.buyeasy.service;

import org.larissa.buyeasy.database.dao.UserDAO;
import org.larissa.buyeasy.database.entity.User;
import org.larissa.buyeasy.formbean.RegisterUserFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class UserService {

    @Autowired
    private UserDAO userDao;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createNewUser(RegisterUserFormBean form){
        User user = new User();

        String encoded = passwordEncoder.encode(form.getPassword());
        log.debug("Encoded password: "+encoded);
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPhone(form.getPhone());
        user.setZipCode(form.getZipCode());
        user.setPassword(encoded);


        return userDao.save(user);
    }
}
