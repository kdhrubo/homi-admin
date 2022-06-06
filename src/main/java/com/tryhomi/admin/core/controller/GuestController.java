package com.tryhomi.admin.core.controller;


import com.tryhomi.admin.core.application.UserCommandUseCase;
import com.tryhomi.admin.core.domain.Role;
import com.tryhomi.admin.core.domain.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Locale;


@Controller
@Slf4j
@RequiredArgsConstructor
public class GuestController {

    private final UserCommandUseCase userCommandUseCase;
    private final ModelMapper modelMapper;

    @GetMapping("/signin")
    public String gotoLogin(Model model){
        return "signin";
    }

    @RequestMapping(value = "/signin", params="failed")
    public String failedLogin(Model model) {
        model.addAttribute("failed", true);
        return "signin";
    }

    @GetMapping("/signup")
    public String gotoRegister(Model model){
        log.info("User signup");
        model.addAttribute(new UserRegistrationForm());

        return "signup";
    }

    @PostMapping("/signup")
    public String register(@Valid UserRegistrationForm userRegistrationForm, BindingResult bindingResult, Locale locale,
                           HttpServletRequest httpServletRequest,
                           Model model){
        log.info("Registering user - {}", userRegistrationForm);
        model.addAttribute(userRegistrationForm);

        if (bindingResult.hasErrors()) {
            log.info("Form validation error. - {}", bindingResult);
            log.info("Model - {}", model);
            return "signup";
        }

        UserCommandUseCase.UserRegistrationCommand userRegistrationCommand =
        modelMapper.map(userRegistrationForm, UserCommandUseCase.UserRegistrationCommand.class);
        userRegistrationCommand.setRole(Role.TENANT_ADMIN);

        userRegistrationCommand.setLocale(locale);
        userRegistrationCommand.setUrl(httpServletRequest.getContextPath());

        User user = userCommandUseCase.register(userRegistrationCommand);

        model.addAttribute("successKey", "success.signup.completed");

        return "signup";
    }

    @Data
    private static class UserRegistrationForm {


        @NotBlank
        String firstName;

        @NotBlank
        String lastName;

        @NotBlank
        String password;

        @Email(message = "Email is not valid.")
        String email;


    }
}
