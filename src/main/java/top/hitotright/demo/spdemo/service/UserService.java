package top.hitotright.demo.spdemo.service;

import org.springframework.stereotype.Service;
import top.hitotright.demo.spdemo.dao.UserRepository;
import top.hitotright.demo.spdemo.entity.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public void add(User user) {
        userRepository.save(user);
    }

    public List<User> list() { return userRepository.findAll();}
}

