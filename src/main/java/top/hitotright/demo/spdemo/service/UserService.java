package top.hitotright.demo.spdemo.service;

import org.springframework.stereotype.Service;
import top.hitotright.demo.spdemo.entity.User;
import top.hitotright.demo.spdemo.dao.UserRepository;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public void add(User user) {
        userRepository.save(user);
    }
}

