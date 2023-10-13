package io.github.hizhangbo.controller;

import cn.shuibo.annotation.Decrypt;
import cn.shuibo.annotation.Encrypt;
import io.github.hizhangbo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Encrypt
    @GetMapping("/encryption")
    public User encryption() {
        User user = new User();
        user.setId(1L);
        user.setName("xxx");
        user.setAge(18);
        return user;
    }

    @Decrypt
    @PostMapping("/decryption")
    public String Decryption(@RequestBody User user) {
        return user.toString();
    }
}
