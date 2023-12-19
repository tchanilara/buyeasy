package org.larissa.buyeasy.security;

import lombok.extern.slf4j.Slf4j;
import org.larissa.buyeasy.database.dao.UserDAO;
import org.larissa.buyeasy.database.dao.UserRoleDAO;
import org.larissa.buyeasy.database.entity.User;
import org.larissa.buyeasy.database.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmailIgnoreCase(username);

        if(user == null){
            throw new UsernameNotFoundException("Username "+ username + " not found in database");
        }

        boolean accountIsEnabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        List<UserRole> userRoles = userRoleDao.findByUserId(user.getId());

        // setup user roles
        Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(userRoles);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), accountIsEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, springRoles);

    }

    public static Collection<? extends GrantedAuthority> buildGrantAuthorities(List<UserRole> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserRole role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().toString()));
        }

        // always add the user role
        authorities.add(new SimpleGrantedAuthority("USER"));

        return authorities;
    }


}
