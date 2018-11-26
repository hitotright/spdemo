package top.hitotright.spdemo.api;

import org.springframework.web.bind.annotation.*;
import top.hitotright.spdemo.model.User;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getList() {
        List<User> l = new ArrayList<User>(users.values());
        return l;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setAge(user.getAge());
        u.setName(user.getName());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
