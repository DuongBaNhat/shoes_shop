package com.sundayteam.backend.modules.security.service;

import com.sundayteam.backend.modules.security.entity.UserEntity;
import com.sundayteam.backend.modules.security.model.user.UserDetailsImpl;
import com.sundayteam.backend.modules.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found with username:" + username);
        return new UserDetailsImpl(user);
    }
}
