package com.example.day12.controller;

import com.example.day12.entity.User;
import com.example.day12.reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/process_register")
    public String processRegistration(@ModelAttribute User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword()); // Sử dụng PasswordEncoder đã tiêm
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "process_register";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

//    @PostMapping("/logout")
//    public String handleLogOut(HttpSession session) {
//        session.invalidate();
//        return "redirect:/";
//    }
}
