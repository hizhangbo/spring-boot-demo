package io.github.hizhangbo.controller;

import io.github.hizhangbo.domain.User;
import io.github.hizhangbo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final UserMapper userMapper;

    @GetMapping
    public String index() {
        final List<User> allUsers = userMapper.findAllUsers();
        System.out.println(allUsers);
        return "index";
    }
}
