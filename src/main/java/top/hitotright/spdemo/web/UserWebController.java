package top.hitotright.spdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.hitotright.spdemo.domain.User;
import top.hitotright.spdemo.domain.UserRepository;
import top.hitotright.spdemo.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserWebController {

    @Resource
    private UserService userService;

    @RequestMapping("/add")
    public String add(ModelMap map){
        map.put("url","/user/add_save");
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
