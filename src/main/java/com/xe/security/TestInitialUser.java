package com.xe.security;

import com.xe.entity.User;
import com.xe.entity.api.Exchange;
import com.xe.entity.sec_api.XUserDetails;
import com.xe.enums.XCurrency;


import com.xe.repo.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//
//public class TestInitialUser {
//
//    private final UserDetailsRepo repo;
//    private final PasswordEncoder enc;
//
//    public TestInitialUser(UserDetailsRepo repo, PasswordEncoder enc) {
//        this.repo = repo;
//        this.enc = enc;
//    }

//    @Bean
//    public void create() {
//        List<Exchange> f =new ArrayList<>();
//        f.add(new Exchange(XCurrency.EUR,XCurrency.BGN,3434.2, null));
//        f.add(new Exchange(XCurrency.CHF,XCurrency.BGN,123.1, null));
//
//        List<Exchange> aq =new ArrayList<>();
//        aq.add(new Exchange(XCurrency.EUR,XCurrency.BGN,3434.2, null));
//        aq.add(new Exchange(XCurrency.EUR,XCurrency.BGN,3434.2, null));
//
//
//        List<Exchange> as =new ArrayList<>();
//        as.add(new Exchange(XCurrency.EUR,XCurrency.BGN,3434.2, null));
//        as.add(new Exchange(XCurrency.EUR,XCurrency.BGN,3434.2, null));
//
//
//        XUserDetails user1 = new XUserDetails(1,"Ferid", enc.encode(new String("111")), "f@mail.ru", f);
//        XUserDetails user2 = new XUserDetails(2,"Aqil",enc.encode(new String("222")), "aqil99@gmail.com", aq);
//        XUserDetails user3 = new XUserDetails(3,"Samir", enc.encode(new String("333")), "s@mail.ru", as);
//        repo.saveAll(Arrays.asList(user1,user2,user3));
//    }
//}
