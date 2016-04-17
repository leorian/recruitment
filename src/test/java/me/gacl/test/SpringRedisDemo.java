package me.gacl.test;

/**
 * Created by xiezhonggui on 16-4-17.
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ????
 * Created by qqr on 15/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-redis.xml")
public class SpringRedisDemo {

    @Autowired
    RedisTemplate jedisTemplate;

    @Test
    public void putAndGet(){
        jedisTemplate.opsForHash().put("user","name","??");
        Object name =  jedisTemplate.opsForHash().get("user","name");
        System.out.println(name);
    }
}