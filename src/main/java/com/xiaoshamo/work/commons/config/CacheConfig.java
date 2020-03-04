package com.xiaoshamo.work.commons.config;

import com.xiaoshamo.work.commons.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@Configuration
public class CacheConfig {

    private static final Logger LOG = LoggerFactory.getLogger(CacheConfig.class);

    /**
     * 自定义 Cache 的 key 生成器
     */
    @Bean("oneKeyGenerator")
    public KeyGenerator getKeyGenerator (){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                // 无返回值的方法，不需要缓存
                if (Void.class == method.getReturnType()) {
                    LOG.error("无返回值的方法不可缓存 {}", method.getName());
                    return null;
                }
                Object result = null;

                // 组合key
                StringBuilder sb = new StringBuilder();
                // 类名
                sb.append(method.getDeclaringClass().getSimpleName());
                // 方法名
                sb.append("/").append(method.getName()).append("/");
                // 参数
                if (params.length > 0) {
                    for (Object param : params) {
                        // 值为null
                        if (param == null) {
                            sb.append(53).append("/");
                            continue;
                        }
                        // 值的类型为String
                        if (String.class.isAssignableFrom(param.getClass())
                                || Number.class.isAssignableFrom(param.getClass())) {
                            sb.append(param).append("/");
                            continue;
                        }
                        // 类型为Date，则用时间戳
                        if (Date.class.isAssignableFrom(param.getClass())) {
                            Date date = (Date) param;
                            sb.append(date.getTime()).append("/");
                            continue;
                        }

                        // TODO List参数类型处理
                        if (List.class.isAssignableFrom(param.getClass())) {
                            @SuppressWarnings("unchecked")
                            List<Object> objs = (List<Object>) param;
                            for (Object object : objs) {
                                // 实现了接口ListKeyParam的类才进行处理
                                if (object instanceof ListKeyParam) {
                                    sb.append(((ListKeyParam) object).getKey());
                                }
                            }
                            continue;
                        }

                        LOG.debug("缓存数据时存在不可识别的参数类型 {},method={}", param.getClass(), method);
                        sb.append(param).append("/");
                    }
                }
                // TODO md5-->number 可将key缩短节省内存空间
                try {
                    result = generate(target, method, MD5Util.getMD5(sb.toString()));
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                }

                LOG.debug("Cache key = {},method={}", result, method);
                return result;
            }
        } ;
    }
}