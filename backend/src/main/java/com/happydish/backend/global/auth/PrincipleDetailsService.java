package com.happydish.backend.global.auth;

import com.happydish.backend.user.entity.User;
import com.happydish.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipleDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    //리턴되는 PrincipleDetails -> Authentication -> session
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userEntity = userRepository.findByEmail(username);
        if(userEntity.isPresent()){
            return new PrincipleDetails(userEntity.get());
        }
        return null;
    }
}
