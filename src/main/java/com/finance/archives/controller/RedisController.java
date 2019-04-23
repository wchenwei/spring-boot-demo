package com.finance.archives.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

@Controller
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate = null;
    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @RequestMapping("/stringAndHash")
    @ResponseBody
    public Map<String,Object> testStringAndHash(){
        Map<String,Object> map = new HashMap<>();
        // jdk序列化器  不能运算
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("key1","value1");
        valueOperations.set("int_key","1");
        ValueOperations stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set("int","1");
        // 使用运算
        stringValueOperations.increment("int",1);
        // 获取底层jedis连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory()
                        .getConnection().getNativeConnection();
        jedis.decr("int");
        map.put("field1","value1");
        map.put("field2","value2");
        // 存入一个散列数据
        stringRedisTemplate.opsForHash().putAll("map",map);
        // 新增一个字段
        stringRedisTemplate.opsForHash().put("map","field3","value3");
        // 绑定散列操作的key，可以连续对一个散列数据类型做操作
        BoundHashOperations<String, Object, Object> hashOps = stringRedisTemplate.boundHashOps("map");
        hashOps.delete("field1","field2");
        hashOps.put("field4","value4");
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        return result;
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> getlist(){
        // 插入两个列表，注意顺序
        // 从左到右的顺序 v10,v8,v6,v4,v2
        stringRedisTemplate.opsForList().leftPushAll("list1","v2","v4","v6","v8","v10");
        // 从左到右的顺序 v1,v2,v3,v4,v5
        stringRedisTemplate.opsForList().rightPushAll("list2","v1","v2","v3","v4","v5");
        // 绑定list2链表操作
        BoundListOperations listOperations = stringRedisTemplate.boundListOps("list2");
        // 从右边弹出一个成员
        Object rightPop = listOperations.rightPop();
        // 获取定位元素
        Object o = listOperations.index(1);
        listOperations.leftPush("v0");
        // 链表长度
        Long size = listOperations.size();
        // 链表定位元素
        List list = listOperations.range(0, size - 2);
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        return result;

    }

    @RequestMapping("/set")
    @ResponseBody
    public Map<String,Object> getSet(){
        stringRedisTemplate.opsForSet().add("set1","v1","v1","v2","v3","v4","v5");
        stringRedisTemplate.opsForSet().add("set2","v2","v4","v6","v8","v10");
        // 绑定set1集合操作
        BoundSetOperations setOperations = stringRedisTemplate.boundSetOps("set1");
        // 增加两个元素
        setOperations.add("v6","v7");
        // 删除两个元素
        setOperations.remove("v1","v7");
        // 返回所有元素
        Set members = setOperations.members();
        // 成员数
        Long size = setOperations.size();
        // 求交集
        Set intersect = setOperations.intersect("set2");
        // 求交集，并用新集合inter保存
        setOperations.intersectAndStore("set2","inter");
        // 求差集
        Set diff = setOperations.diff("set2");
        // 求差集，并用新集合diff保存
        setOperations.diffAndStore("set2","diff");
        // 求并集
        Set union = setOperations.union("set2");
        setOperations.unionAndStore("set2","union");
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        return result;
    }

    @RequestMapping("/zSet")
    @ResponseBody
    public Map<String,Object> zSet(){
        Set<ZSetOperations.TypedTuple<String>> typedTuples = new HashSet<>();
        for (int i=1 ;i<=9;i++){
            double score = i*0.1;
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<String>("value"+i,score);
            typedTuples.add(typedTuple);
        }
        // 插入元素
        stringRedisTemplate.opsForZSet().add("zSet1",typedTuples);
        BoundZSetOperations zSetOperations = stringRedisTemplate.boundZSetOps("zSet1");
        // 增加一个元素
        zSetOperations.add("value10",0.26);
        Set<String> setRange = zSetOperations.range(1,6);
        // 按分数排序获取有序集合
        Set<String> setScore = zSetOperations.rangeByScore(0.2,0.6);
        // 定义值范围
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        // 大于value3
        RedisZSetCommands.Range value3 = range.gt("value3");
//        // 大于等于value3
//        range.gte("value3");
//        // 小于value3
//        range.lt("value3");
//        // 小于等于value3
//        range.lte("value3");
        // 按字符串排序
        Set<String> setLex = zSetOperations.rangeByLex(range);
        // 删除元素
        zSetOperations.remove("value9","value2");
        // 求分数
        Double score = zSetOperations.score("value8");
        // 在下标区间下，按分数排序，同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> withScores = zSetOperations.rangeWithScores(1, 6);
        // 在下标区间下，按分数排序，同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> scoreSet = zSetOperations.rangeByScoreWithScores(1,6);
        Set<String> reverseSet = zSetOperations.reverseRange(2,8);
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        return result;
    }

    @RequestMapping("/multi")
    @ResponseBody
    public Map<String,Object> multi(){
        stringRedisTemplate.opsForValue().set("key1","1");
        List list = (List) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                // 设置监控key1
                redisOperations.watch("key1");
                // 开启事务
                redisOperations.multi();
                redisOperations.opsForValue().increment("key1",1);
                redisOperations.opsForValue().set("key2","value2");
                Object value2 = redisOperations.opsForValue().get("key2");
                redisOperations.opsForValue().set("key3","value3");
                return redisOperations.exec();
            }
        });
        System.out.println(list);
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        return result;
    }

    @RequestMapping("/pipeLine")
    @ResponseBody
    public Map<String,Object> pipeLine(){
        Long start = System.currentTimeMillis();
        List list = redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                for (int i =1;i<=100000;i++){
                    redisOperations.opsForValue().set("pipleLine_"+i,"value_"+i);
                    String value = (String) redisOperations.opsForValue().get("pipleLine_" + i);
                    if (i==100000){
                        System.out.println(value);
                    }
                }
                return null;
            }
        });
        Long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start)+"毫秒");
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        return result;
    }
}
