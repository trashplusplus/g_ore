package com.example.g_ore;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {


    private MyUserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public PostController(MyUserServiceImpl userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/done")
    public String doneMethod(@ModelAttribute("myUser") MyUser myUser){

        String encodedPassword = passwordEncoder.encode(myUser.getPassword());
        System.out.println("Зашифрованный парорль: " + encodedPassword);

        myUser.setPassword(encodedPassword);
        System.out.println("Пароль пользователя: " + myUser.getPassword());
        System.out.println("Записан: " + myUser);
        userService.save(myUser);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUserByAdminMethod(@RequestParam("username") String username){

        MyUser deletedUser = userService.findByUsername(username);

        System.out.println("Удален: " + deletedUser);
        userService.delete(deletedUser);
        userService.flush();

        return "redirect:/";
    }



}
