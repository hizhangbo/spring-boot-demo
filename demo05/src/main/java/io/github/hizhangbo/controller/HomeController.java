package io.github.hizhangbo.controller;

import io.github.hizhangbo.GuavaCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private GuavaCacheManager guavaCacheManager;

    @GetMapping
    public String index() {
        String key = "cckk";
        final String value = guavaCacheManager.get(key);

        if (StringUtils.hasText(value)) {
            return value;
        }

        redisTemplate.opsForValue().set(key, "123456789");

        final String cckk = redisTemplate.opsForValue().get("cckk");

        guavaCacheManager.put(key, cckk);
        return cckk;
    }
}
