package com.finance.archives.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/lua")
@Controller
public class RedisLuaController {

    @Autowired
    private RedisTemplate redisTemplate = null;

    @RequestMapping("/testLua")
    @ResponseBody
    public Map<String,Object> testLua(){
        DefaultRedisScript<String> rs = new DefaultRedisScript<>();
        // 设置脚本
        rs.setScriptText("return 'hello redis'");
        rs.setResultType(String.class);
        RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
        String str = (String) redisTemplate.execute(rs, redisSerializer, redisSerializer, null);
        Map<String,Object> result = new HashMap<>();
        result.put("result",str);
        result.put("success",true);
        return result;
    }
}
