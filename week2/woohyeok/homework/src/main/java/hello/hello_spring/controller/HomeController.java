package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")//localhost8080 했을 때의 첫 화면
    public String home() {
        return "home";
    }
}