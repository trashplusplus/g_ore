package com.example.g_ore;


import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GetController {

    InMemoryUserDetailsManager manager;
    //JdbcUserDetailsManager jdbcUserDetailsManager;
    MyUserServiceImpl userService;
    private final MyUserDAO myUserDAO;

    public GetController(MyUserServiceImpl userService,
                         MyUserDAO myUserDAO){
        manager = new InMemoryUserDetailsManager();
        this.userService = userService;
        this.myUserDAO = myUserDAO;
    }

    @GetMapping(value = {"/", "/main"})
    public String mainPage(){
        return "main";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        model.addAttribute("currentName", currentName);
        return "admin";
    }

    @GetMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("myUser", new MyUser());
        return "register";
    }


    @GetMapping("/create")
    public String newWord(@RequestParam(name = "name", required = true, defaultValue = "1") String name, Model model){
        //model.addAttribute("name", name);

        return "redirect:/";
    }


    @GetMapping("/user")
    public String user(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        MyUser myUser = userService.findByUsername(currentName);

        model.addAttribute("name", currentName);
        model.addAttribute("id", myUser.getId());
        model.addAttribute("password", myUser.getPassword());
        return "user";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/top")
    public String top(Model model){
        model.addAttribute("userSize", userService.getAllMyUsers().size());
        model.addAttribute("users", userService.getAllMyUsers());
        return "top";
    }


    @Bean
    public UserDetailsService userDetailsService(){
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin").password("admin").roles("ADMIN").build());

        for(MyUser user: userService.getAllMyUsers()){
            manager.createUser(User.withDefaultPasswordEncoder().username(user.getUsername()
            ).password(user.getPassword()).roles("USER").build());
        }


        //manager.createUser(User.withDefaultPasswordEncoder().username("james").password("admin").roles("USER").build());
        return manager;
    }

}
