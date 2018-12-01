package top.hitotright.spdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String home(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://www.test.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }
}
