package br.com.watchwatt.watchwatt.service.Authentication;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();
}
