package com.xiaoshamo.work.commons.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CommonRedisUtil
 * @Description: 公告缓存数据库
 * @Auther: 35543
 * @Date: 2020-02-13 15:52
 * @Version: 1.0.0
 */
public class CommonRedisUtil {

    /**
     * 公共缓存区存值
     */
    @Autowired
    private StringRedisTemplate sredisTemplate;

    /**
     * 公共缓存区存对象
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 是否存在某个key----字符串----公共缓存区
     *
     * @param key
     * @return
     * @throws Exception
     */
    public boolean has(String key) throws Exception {
        return sredisTemplate.hasKey(key);
    }

    /**
     * 读取指定key的值----字符串----公共缓存区
     *
     * @param key
     * @return
     * @throws Exception
     */
    public String queryValue(String key) throws Exception {
        return sredisTemplate.opsForValue().get(key);
    }

    /**
     * 存储值----字符串----公共缓存区
     *
     * @param key
     * @param value
     * @throws Exception
     */
    public void saveValue(String key, String value) throws Exception {
        sredisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储值并指定失效时间----字符串----公共缓存区
     *
     * @param key
     * @param value
     * @param liveTime 失效时间，单位：秒
     * @throws Exception
     */
    public void saveValue(String key, String value, long liveTime) throws Exception {
        sredisTemplate.opsForValue().set(key, value, liveTime, TimeUnit.SECONDS);
    }

    /**
     * 批量存储值----字符串----公共缓存区
     *
     * @param param
     * @throws Exception
     */
    public void saveValues(Map<String, String> param) throws Exception {
        sredisTemplate.opsForValue().multiSet(param);
    }

    /**
     * 设置某key的过期时间，单位：秒----字符串----公共缓存区
     *
     * @param key
     * @param liveTime
     * @throws Exception
     */
    public void setExpire(String key, long liveTime) throws Exception {
        sredisTemplate.expire(key, liveTime, TimeUnit.SECONDS);
    }

    /**
     * 根据指定key删除值----字符串----公共缓存区
     *
     * @param key
     * @throws Exception
     */
    public void delValue(String key) throws Exception {
        sredisTemplate.delete(key);
    }

    /**
     * 是否存在某个key----对象----公共缓存区
     *
     * @param key
     * @return
     * @throws Exception
     */
    public boolean hasObj(String key) throws Exception {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取指定key的值----对象----公共缓存区
     *
     * @param key
     * @return
     * @throws Exception
     */
    public Object queryObj(String key) throws Exception {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 存储值----对象----公共缓存区
     *
     * @param key
     * @param value
     * @throws Exception
     */
    public void saveObj(String key, Object value) throws Exception {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储值并指定失效时间----对象----公共缓存区
     *
     * @param key
     * @param value
     * @param liveTime 失效时间，单位：秒
     * @throws Exception
     */
    public void saveObj(String key, Object value, long liveTime) throws Exception {
        redisTemplate.opsForValue().set(key, value, liveTime, TimeUnit.SECONDS);
    }

    /**
     * 批量存储值----对象----公共缓存区
     *
     * @param param
     * @throws Exception
     */
    public void saveObjs(Map<String, String> param) throws Exception {
        redisTemplate.opsForValue().multiSet(param);
    }

    /**
     * 设置某key的过期时间，单位：秒----对象----公共缓存区
     *
     * @param key
     * @param liveTime
     * @throws Exception
     */
    public void setObjExpire(String key, long liveTime) throws Exception {
        redisTemplate.expire(key, liveTime, TimeUnit.SECONDS);
    }

    /**
     * 根据指定key删除值----对象----公共缓存区
     *
     * @param key
     * @throws Exception
     */
    public void delObj(String key) throws Exception {
        sredisTemplate.delete(key);
    }

    // ---------------------------------------Hash操作
    // --------------------------------字符串

    /**
     * 存储hash----字符串----公共缓存区
     *
     * @param hashName hash名称
     * @param param    键值对
     * @throws Exception
     */
    public void saveHash(String hashName, Map<String, String> param) throws Exception {
        sredisTemplate.opsForHash().putAll(hashName, param);
    }

    /**
     * 更新指定hash的指定key的值----字符串----公共缓存区
     *
     * @param hashName
     * @param key
     * @param value
     * @throws Exception
     */
    public void saveHash(String hashName, String key, String value) throws Exception {
        sredisTemplate.opsForHash().put(hashName, key, value);
    }

    /**
     * 读取指定hash里指定key的值----字符串----公共缓存区
     *
     * @param hashName hash名称
     * @param key      键
     * @return
     * @throws Exception
     */
    public String queryHash(String hashName, String key) throws Exception {
        Object obj = sredisTemplate.opsForHash().get(hashName, key);
        return obj == null ? "" : (String) obj;
    }

    /**
     * 读取指定hash里所有值----字符串----公共缓存区
     *
     * @param hashName
     * @return
     * @throws Exception
     */
    public Map<Object, Object> queryHashAll(String hashName) throws Exception {
        return sredisTemplate.opsForHash().entries(hashName);
    }

    /**
     * 查询指定hash里是否存在某key----字符串
     *
     * @param hashName
     * @param key
     * @return
     * @throws Exception
     */
    public boolean queryHashHas(String hashName, String key) throws Exception {
        return sredisTemplate.opsForHash().hasKey(hashName, key);
    }

    /**
     * 删除指定hash里的指定key----字符串----公共缓存区
     *
     * @param hashName
     * @param key
     * @throws Exception
     */
    public void delHashKey(String hashName, String... key) throws Exception {
        sredisTemplate.opsForHash().delete(hashName, key);
    }

    /**
     * 指定hash指定key增加n----字符串
     *
     * @param hashName
     * @param key
     * @param n
     * @throws Exception
     */
    public void hashIncrement(String hashName, String key, int n) throws Exception {
        sredisTemplate.opsForHash().increment(hashName, key, n);
    }

    /**
     * 指定hash指定key增加n----字符串
     *
     * @param hashName
     * @param key
     * @param n
     * @throws Exception
     */
    public void hashIncrement(String hashName, String key, double n) throws Exception {
        sredisTemplate.opsForHash().increment(hashName, key, n);
    }

    // --------------------------------对象

    /**
     * 存储hash----对象----公共缓存区
     *
     * @param hashName hash名称
     * @param param    键值对
     * @throws Exception
     */
    public void saveHashObj(String hashName, Map<Object, Object> param) throws Exception {
        redisTemplate.opsForHash().putAll(hashName, param);
    }

    /**
     * 存储hash----对象----公共缓存区
     *
     * @param hashName hash名称
     * @param param    键值对
     * @throws Exception
     */
    public void saveHashObjs(String hashName, Map<String, Object> param) throws Exception {
        redisTemplate.opsForHash().putAll(hashName, param);
    }

    /**
     * 更新指定hash的指定key的值----对象----公共缓存区
     *
     * @param hashName
     * @param key
     * @param value
     * @throws Exception
     */
    public void saveHashObj(String hashName, Object key, Object value) throws Exception {
        redisTemplate.opsForHash().put(hashName, key, value);
    }

    /**
     * 读取指定hash里指定key的值----对象----公共缓存区
     *
     * @param hashName hash名称
     * @param key      键
     * @return
     * @throws Exception
     */
    public Object queryHashObj(String hashName, Object key) throws Exception {
        Object obj = redisTemplate.opsForHash().get(hashName, key);
        return obj;
    }

    /**
     * 读取指定hash里所有值----对象----公共缓存区
     *
     * @param hashName
     * @return
     * @throws Exception
     */
    public Map<Object, Object> queryHashAllObj(String hashName) throws Exception {
        return redisTemplate.opsForHash().entries(hashName);
    }

    /**
     * 查询指定hash里是否存在某key----对象----公共缓存区
     *
     * @param hashName
     * @param key
     * @return
     * @throws Exception
     */
    public boolean queryHashHasObj(String hashName, Object key) throws Exception {
        return redisTemplate.opsForHash().hasKey(hashName, key);
    }

    /**
     * 删除指定hash里的指定key----对象----公共缓存区
     *
     * @param hashName
     * @param key
     * @throws Exception
     */
    public void delHashObjKey(String hashName, Object... key) throws Exception {
        redisTemplate.opsForHash().delete(hashName, key);
    }

    /**
     * 指定hash指定key增加n----对象----公共缓存区
     *
     * @param hashName
     * @param key
     * @param n
     * @throws Exception
     */
    public void hashIncrement(String hashName, Object key, int n) throws Exception {
        redisTemplate.opsForHash().increment(hashName, key, n);
    }

    // ---------------------------------------set操作
    // --------------------------------字符串

    /**
     * 创建set，如果存在则添加值----字符串----公共缓存区
     *
     * @param setName
     * @param vals
     * @throws Exception
     */
    public void saveSet(String setName, String[] vals) throws Exception {
        sredisTemplate.opsForSet().add(setName, vals);
    }

    /**
     * 返回set长度----字符串----公共缓存区
     *
     * @param setName
     * @return
     * @throws Exception
     */
    public long querySetSize(String setName) throws Exception {
        return sredisTemplate.opsForSet().size(setName);
    }

    /**
     * 指定值在set中是否已经存在----字符串----公共缓存区
     *
     * @param setName
     * @param val
     * @return
     * @throws Exception
     */
    public boolean setValIsExist(String setName, String val) throws Exception {
        return sredisTemplate.opsForSet().isMember(setName, val);
    }

    /**
     * set操作，获取所有值----字符串----公共缓存区
     *
     * @param setName
     * @return
     * @throws Exception
     */
    public Set<String> queryMembers(String setName) throws Exception {
        return sredisTemplate.opsForSet().members(setName);
    }

    /**
     * 批量删除---key值以某个字符串开头---公共缓存区
     *
     * @param keys
     * @throws Exception
     */
    public void deleteSet(String keys) throws Exception {
        Set<Object> keysSet = redisTemplate.keys(keys + "*");
        redisTemplate.delete(keysSet);
    }

    // ---------------------------------------列表list操作
    // --------------------------------字符串

    /**
     * 列表操作，左插入值----字符串----公共缓存区
     *
     * @param listName
     * @param val
     * @return 返回的结果为推送操作后的列表的长度
     * @throws Exception
     */
    public long saveListLpush(String listName, String val) throws Exception {
        return sredisTemplate.opsForList().leftPush(listName, val);
    }

    /**
     * 列表操作，左批量插入值----字符串----公共缓存区
     *
     * @param listName
     * @param vals
     * @return
     * @throws Exception
     */
    public long saveListLpush(String listName, List<String> vals) throws Exception {
        return sredisTemplate.opsForList().leftPushAll(listName, vals);
    }

    /**
     * 列表操作，右取值----字符串----公共缓存区
     *
     * @param listName
     * @return
     * @throws Exception
     */
    public String queryListRpop(String listName) throws Exception {
        return sredisTemplate.opsForList().rightPop(listName);
    }

    /**
     * 列表操作，获取列表长度----字符串----公共缓存区
     *
     * @param listName
     * @return
     * @throws Exception
     */
    public long queryListSize(String listName) throws Exception {
        return redisTemplate.opsForList().size(listName);
    }

    // ---------------------------------------zset操作
    // --------------------------------字符串

    /**
     * zset操作----批量存储
     *
     * @param zsetName
     * @param vals
     * @throws Exception
     */
    public void saveZSet(String zsetName, Set<ZSetOperations.TypedTuple<String>> vals) throws Exception {
        sredisTemplate.opsForZSet().add(zsetName, vals);
    }

    /**
     * zset操作----单独存储
     *
     * @param zsetName
     * @param value
     * @param score
     * @throws Exception
     */
    public void saveZSet(String zsetName, String value, double score) throws Exception {
        sredisTemplate.opsForZSet().add(zsetName, value, score);
    }

    /**
     * 批量存储Zset
     *
     * @param zsetName
     * @param zsetList
     * @throws Exception
     */
    public void saveHashZset(String zsetName, List<Map<String, Double>> zsetList) throws Exception {
        if (null != zsetList && zsetList.size() > 0) {
            ZSetOperations<String, String> zset = sredisTemplate.opsForZSet();
            Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
            ZSetOperations.TypedTuple<String> tuple = null;
            for (Map<String, Double> zsetMap : zsetList) {
                for (String key : zsetMap.keySet()) {
                    tuple = new DefaultTypedTuple<String>(key, zsetMap.get(key));
                    tuples.add(tuple);
                }
            }
            zset.add(zsetName, tuples);
        }
    }


    /**
     * zset操作----顺序查询
     *
     * @param zsetName
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public Set<String> queryZSetASC(String zsetName, long start, long end) throws Exception {
        return sredisTemplate.opsForZSet().range(zsetName, start, end);
    }

    /**
     * zset操作----逆序查询
     *
     * @param zsetName
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public Set<String> queryZSetDESC(String zsetName, long start, long end) throws Exception {
        return sredisTemplate.opsForZSet().reverseRange(zsetName, start, end);
    }

    /**
     * 删除指定Zset里的指定key----字符串----公共缓存区
     *
     * @param hashName
     * @param key
     * @throws Exception
     */
    public void removeHashZsetKey(String hashName, String key) throws Exception {
        sredisTemplate.opsForZSet().remove(hashName, key);
    }

    /**
     * 顺序---删除第一条----字符串----公共缓存区
     *
     * @param zsetName
     * @throws Exception
     */
    public void removeHashZsetFirst(String zsetName) throws Exception {
        sredisTemplate.opsForZSet().removeRange(zsetName, 0, 0);
    }

    /**
     * 顺序---删除最后一条----字符串----公共缓存区
     *
     * @param zsetName
     * @throws Exception
     */
    public void removeHashZsetLast(String zsetName) throws Exception {
        sredisTemplate.opsForZSet().removeRange(zsetName, -1, -1);
    }

    /**
     * 添加一条---指定Zset里的指定value值并设置排序----字符串----公共缓存区
     *
     * @param hashName
     * @param key
     * @param score
     * @throws Exception
     */
    public void addHashZsetKey(String hashName, String key, double score) throws Exception {
        sredisTemplate.opsForZSet().add(hashName, key, score);
    }


    /**
     * 操作ZSet，插入一条记录并将score设置为最大，并删除第一条记录
     *
     * @param zsetName
     * @param newVal
     * @param maxLength 最大长度
     * @throws Exception
     */
    public void insertAddRemoveZSet(String zsetName, String newVal, int maxLength) throws Exception {
        ZSetOperations<String, String> operation = sredisTemplate.opsForZSet();
        long size = operation.size(zsetName);
        Set<ZSetOperations.TypedTuple<String>> set = operation.rangeWithScores(zsetName, size - 1, size - 1);
        double maxScore = 0;
        for (ZSetOperations.TypedTuple<String> tt : set) {
            maxScore = tt.getScore();
        }

        double newScore = maxScore + 1;
        if (size >= maxLength) {
            operation.removeRange(zsetName, 0, 0);
        }
        operation.add(zsetName, newVal, newScore);
    }

}
