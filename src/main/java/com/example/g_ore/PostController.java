package com.example.g_ore;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        myUser.setPassword(encodedPassword);
        //default value = USER
        myUser.setAuthority("USER");
        System.out.println("Записан: " + myUser);
        userService.save(myUser);
        return "redirect:/";
    }

    @PostMapping("/passwordEdit")
    public String doneMethod(@RequestParam("oldPassword") String oldPassword,
                             @RequestParam("newPassword") String newPassword,
                             RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUser currentUser = userService.findByUsername(auth.getName());

        String userPassword = currentUser.getPassword();

        System.out.printf("User password %s \n", userPassword);

        if(passwordEncoder.matches(oldPassword, userPassword)){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            userService.save(currentUser);
            redirectAttributes.addFlashAttribute("passwordChanged", "Success! Password was changed!");

        }else{
            redirectAttributes.addFlashAttribute("invalid", "Current password is wrong");

        }

        return "redirect:/passwordEdit";
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
