package top.hitotright.spdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @RequestMapping(value = "home", method = RequestMethod.GET)
    @ResponseBody
    public String getHome() {
        return "home";
    }


    @RequestMapping("")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }
}
