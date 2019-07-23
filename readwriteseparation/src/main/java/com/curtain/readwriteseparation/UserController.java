package com.curtain.readwriteseparation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Curtain
 * @date 2019/4/23 9:52
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("save")
    public void save(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("dalaoyang");
        user.setCity("beijing");
        userRepository.save(user);
    }

    @GetMapping("getAll")
    public Object getAll() {
        return userRepository.findAll();
    }
}
