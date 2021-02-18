package org.jack.rock.rest;

import org.jack.rock.util.Result;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Greeting
 *
 * @author zhengzhe17
 * @date 2020/7/10
 */
@RestController
@RequestMapping(value = "", produces = "application/json")
public class GreetingRest {
    private StringRedisTemplate stringRedisTemplate;

    public GreetingRest(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/greeting")
    public Result<String> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return Result.success(String.format("Hello %s", name));
    }

    @GetMapping("/cache-test")
    @Cacheable("cache:")
    public String cacheTest() {
        return "aaaaaaaaa";
    }
}
