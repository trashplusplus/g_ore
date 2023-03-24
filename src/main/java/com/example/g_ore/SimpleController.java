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
public class SimpleController {

    InMemoryUserDetailsManager manager;
    MyUserServiceImpl userService;

    public SimpleController(MyUserServiceImpl userService){
        manager = new InMemoryUserDetailsManager();
        this.userService = userService;
    }

    @GetMapping(value = {"/", "/main"})
    public String mainPage(Model model){
        model.addAttribute("userSize", userService.getAllMyUsers().size());
        model.addAttribute("users", userService.getAllMyUsers());

        return "main";
    }

    @GetMapping("/admin")
    public String admin(Model model){
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
    /*


    @GetMapping("/reg")
    public String register(@RequestParam(name = "name", required = true) String name,
                           @RequestParam(name = "password", required = true) String password){

        MyUser user = new MyUser(name, password);
        userDAO.add(user);
        arrayList.add("[USER]: " + name);
        manager.createUser(User.withDefaultPasswordEncoder().username(name).password(password).roles("USER").build());

        return "redirect:/";

    }

     */
    @GetMapping("/getUsers")
    public String getUsers(Model model){
        return "redirect:/";
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

    @GetMapping("/user")
    public String user(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();

        model.addAttribute("name", currentName);
        return "user";
    }


}
