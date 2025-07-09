package com.example.basicSecurity.service;

import com.example.basicSecurity.entity.User;
import com.example.basicSecurity.model.UserPrinciple;
import com.example.basicSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceDetails implements UserDetailsService {



    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if(user == null) {
            System.out.println("no user for ya");
            throw new UsernameNotFoundException("kk");
        }
        return new UserPrinciple(user);
    }
}
