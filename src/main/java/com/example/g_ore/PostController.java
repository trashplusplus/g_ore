package com.example.g_ore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {


    private MyUserServiceImpl userService;

    public PostController(MyUserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping("/done")
    public String doneMethod(@ModelAttribute("myUser") MyUser myUser){
        userService.save(myUser);
        System.out.println("Записан: " + myUser);
        return "user";
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
