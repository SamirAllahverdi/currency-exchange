package com.xe.controller;

import com.xe.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequestMapping("/main-page-authorized")
public class MainPageAuthorizedController {

    private static String fmt(String format, Object... args) {
        return String.format(format, args);
    }

    //TODO must be corrected
    @GetMapping
    public String showMainPageAuthorized(
           @SessionAttribute("user") User user,
            HttpServletRequest httpServletRequest) {
       log.info("MAIN PAGE AUTHORIZED");
        HttpSession session = httpServletRequest.getSession(false);
        log.info(fmt("Found user %s in Main Page Authorized", user));
        return session == null ? "error-404" : "main-page-authorized";
    }
}