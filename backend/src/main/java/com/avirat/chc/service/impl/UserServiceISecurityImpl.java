package com.avirat.chc.service.impl;

import com.avirat.chc.entity.UserEntity;
import com.avirat.chc.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceISecurityImpl  implements UserDetailsService {

    @Autowired
  UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity userEntity = userRepo.findByUserName(userName).orElseThrow(()->  new UsernameNotFoundException("User Deatils Is not found for"+userName));
        return userEntity;
    }
}
