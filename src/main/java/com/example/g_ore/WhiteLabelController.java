package com.example.g_ore;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WhiteLabelController implements ErrorController {

    @GetMapping("/error")
    public String handleError(){
        return "error";
    }

}
