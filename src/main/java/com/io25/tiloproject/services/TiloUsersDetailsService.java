package com.io25.tiloproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.io25.tiloproject.config.TiloUserDetails;
import com.io25.tiloproject.model.TiloUser;
import com.io25.tiloproject.repository.TiloUserRepository;

import java.util.Optional;

@Service
public class TiloUsersDetailsService implements UserDetailsService {

    private TiloUserRepository userRepository;

    @Autowired
    public void setUserRepository(TiloUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<TiloUser> tiloUserByUsername = userRepository.findTiloUserByUsername(username);
        return tiloUserByUsername
                .map(TiloUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found!"));
    }
}
