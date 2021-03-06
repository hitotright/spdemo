package top.hitotright.demo.spdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.hitotright.demo.spdemo.entity.User;
import top.hitotright.demo.spdemo.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserWebController {

    @Resource
    private UserService userService;

    @RequestMapping("")
    public String add(ModelMap map){
        List<User> list = userService.list();
        map.addAttribute("list",list);
        map.addAttribute("url","/user/add_save");
        return "user";
    }

    @RequestMapping(value = "/add_save",method = RequestMethod.POST)
    @ResponseBody
    public String addSave(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }else{
            userService.add(user);
            return "添加成功";
        }
    }
}
