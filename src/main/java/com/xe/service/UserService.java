package com.xe.service;

import com.xe.entity.User;
import com.xe.entity.api.Exchange;
import com.xe.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;
import java.util.function.Supplier;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder enc;

    public void addUser(User user) {
        String encode = enc.encode(user.getPassword());
        user.setPassword(encode);
        user.setMatchingPassword(encode);
        user.setRole("USER");
        userRepository.save(user);
    }

    public void addExchange(String email, Exchange ex) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        User user = byEmail.orElseThrow(RuntimeException::new);
        user.getExchanges().add(ex);
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(enc.encode(password), userId);
    }

    public static String getUserNameFromPrincipal(Principal p) {
        if (p instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken user = (OAuth2AuthenticationToken) p;
            return user.getPrincipal().getAttribute("name");
        } else {
            UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) p;
            User user = (User) userToken.getPrincipal();
            return user.getFullName();
        }
    }


    public static void getUserNameFromPrincipalWithBehaviour(Principal p,
                                                             Runnable behaviour1,
                                                             Runnable behaviour2) {
        if (p instanceof OAuth2AuthenticationToken) {
            behaviour1.run();
        } else {
            behaviour2.run();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        return userRepository.findByEmail(mail)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User: %s isn't found in our DB with that mail", mail)
                ));
    }
}
