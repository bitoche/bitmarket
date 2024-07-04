package ru.bitoche.basemarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bitoche.basemarket.repositories.IAppUserRepository;
import ru.bitoche.basemarket.models.AppUser;
import ru.bitoche.basemarket.models.AppUserDetails;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private IAppUserRepository appUserRepos;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepos.findAppUserByEmail(email);
        return user.map(AppUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException(email + " не найден"));
    }
}
