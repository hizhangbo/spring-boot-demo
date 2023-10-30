package io.github.hizhangbo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

@Component
public class GuavaCacheManager {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final Duration refreshInterval = Duration.ofSeconds(5);
    private final Duration expireInterval = Duration.ofSeconds(10);
    private final long maxSize = 1000L;

    LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(maxSize)
            .refreshAfterWrite(refreshInterval)
            .expireAfterAccess(expireInterval)
            .recordStats()
            .build(new CacheLoader<>() {
                @Override
                public String load(@Nonnull String key) {
                    System.out.println("cache size: " + cache.stats());

                    final String value = redisTemplate.opsForValue().get(key);
                    if (StringUtils.hasText(value)) {
                        return value;
                    }
                    return "";
                }
            });

    public String get(String key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public void delete(String key) {
        cache.invalidate(key);
    }
}
