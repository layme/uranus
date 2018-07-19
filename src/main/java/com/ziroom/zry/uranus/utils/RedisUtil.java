package com.ziroom.zry.uranus.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author renhy
 * @version 1.0
 * @Date Created in 2018年07月18日 21:17
 * @since 1.0
 */
@Slf4j
@Component(value = "redisUtil")
public class RedisUtil {
    @Autowired
    private StringRedisTemplate redisTpl;

    /**
     * 保存数据到redis中，不设置过期时间
     * @author renhy
     * @created 2018年07月18日 21:18:31
     * @param
     * @return
     */
    public boolean set(String key, String value){
        try{
            redisTpl.opsForValue().set(key, value);
            return true;
        }catch(Exception e){
            log.error("operate redis meet an error => {}", e);
            return false;
        }
    }

    /**
     * 保存数据到redis中，设置过期时间，单位默认位秒
     * @author renhy
     * @created 2018年07月18日 21:23:41
     * @param
     * @return
     */
    public boolean setExSeconds(String key, String value, long time) {
        return this.setEx(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 保存数据到redis中，设置过期时间
     * @author renhy
     * @created 2018年07月18日 21:28:37
     * @param
     * @return
     */
    public boolean setEx(String key, String value, long time, TimeUnit timeUnit) {
        try{
            redisTpl.opsForValue().set(key, value, time, timeUnit);
            return true;
        }catch(Exception e){
            log.error("operate redis meet an error => {}", e);
            return false;
        }
    }

    /**
     * 通过key获取redis里面的值
     * @author renhy
     * @created 2018年07月18日 21:18:40
     * @param
     * @return
     */
    public String get(String key){
        return redisTpl.opsForValue().get(key);
    }

    /**
     * 判断key是否存在
     * @author renhy
     * @created 2018年07月18日 21:25:48
     * @param
     * @return
     */
    public boolean hasKey(String key){
        try {
            return redisTpl.hasKey(key);
        } catch (Exception e) {
            log.error("operate redis meet an error => {}", e);
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     * @author renhy
     * @created 2018年07月18日 21:31:10
     * @param
     * @return
     */
    public long getExpire(String key){
        return redisTpl.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     * @author renhy
     * @created 2018年07月18日 21:32:00
     * @param
     * @return
     */
    public void del(String ... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTpl.delete(key[0]);
            }else{
                redisTpl.delete(Lists.newArrayList(key));
            }
        }
    }
}
