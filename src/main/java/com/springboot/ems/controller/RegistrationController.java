package com.springboot.ems.controller;

import com.springboot.ems.entity.User;
import com.springboot.ems.model.UserDto;
import com.springboot.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto UserDto, BindingResult result, Model model) {
        User existing = userService.findUserByEmail(UserDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", UserDto);
            return "registration";
        }
        userService.save(UserDto);
        return "redirect:/registration?success";
    }
}
