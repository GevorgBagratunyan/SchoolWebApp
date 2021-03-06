package com.gevbagratunyan.school.service.security;

import com.gevbagratunyan.school.entity.models.User;
import com.gevbagratunyan.school.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        MyUserDetails userDetails = new MyUserDetails(user);
        return userDetails;
    }
}
