package io.github.hizhangbo.controller;

import io.github.hizhangbo.GuavaCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

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

    // 测试 setIfAbsent
    @GetMapping("/absent")
    public String redisIfAbsent() {

        final Boolean absent1 = redisTemplate.opsForValue().setIfAbsent("absent", "123456789", 3, TimeUnit.SECONDS);
        final Boolean absent2 = redisTemplate.opsForValue().setIfAbsent("absent", "123456789", 3, TimeUnit.SECONDS);

        log.info("1: {}, 2: {}", absent1, absent2);
        return "ok";
    }
}
