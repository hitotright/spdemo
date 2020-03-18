package top.hitotright.demo.spdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.hitotright.demo.spdemo.properties.BlogProperties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    private BlogProperties blog;

    @RequestMapping("")
    public String home(ModelMap map) {

        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", blog.getName());
        map.addAttribute("author", blog.getAuthor());
        List<HashMap>  list = Arrays.asList(new HashMap(2){{
            put("name","home");put("url","/home");
        }},new HashMap(2){{
            put("name","user");put("url","/user");
        }});
        map.addAttribute("menu", list);
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String getHome(ModelMap map) {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
