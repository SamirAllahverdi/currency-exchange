package com.xe.security;

import com.xe.entity.User;
import com.xe.entity.api.Exchange;
import com.xe.enums.XCurrency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class UserDetailsHashMap {

  private final Map<String, String> storage = new HashMap<>();

  public UserDetailsHashMap() {
    List<Exchange> f = new ArrayList<>();
            f.add(new Exchange(XCurrency.EUR, XCurrency.BGN, 3434.2, null));
            f.add(new Exchange(XCurrency.CHF, XCurrency.BGN, 123.1, null));

            List<Exchange> aq = new ArrayList<>();
            aq.add(new Exchange(XCurrency.EUR, XCurrency.BGN, 3434.2, null));
            aq.add(new Exchange(XCurrency.EUR, XCurrency.BGN, 3434.2, null));


            List<Exchange> as = new ArrayList<>();
            as.add(new Exchange(XCurrency.EUR, XCurrency.BGN, 3434.2, null));
            as.add(new Exchange(XCurrency.EUR, XCurrency.BGN, 3434.2, null));


          User user1 = new User("Ferid", "111", "111", "f@mail.ru", f);
            User user2 = new User("Aqil", "222", "222", "aqil99@gmail.com", aq);
            User user3 = new User("Samir", "333", "333", "s@mail.ru", as);

    storage.put(user1.getEmail(),  user1.getPassword());
    storage.put(user2.getEmail(),  user2.getPassword());
    storage.put(user3.getEmail(),  user3.getPassword());

  }

  private UserDetails mapper(Map.Entry<String, String> entry) {
    return org.springframework.security.core.userdetails.User
        .withDefaultPasswordEncoder()
        .username(entry.getKey())
        .password(entry.getValue())
        .roles() // all of the users have only one role: "USER"
        .build();
  }

  @Bean
  public UserDetailsService udsHashMap() {
    Collection<UserDetails> data = storage.entrySet().stream()
        .map(this::mapper)
        .collect(Collectors.toSet());
    return new InMemoryUserDetailsManager(data);
  }
}
