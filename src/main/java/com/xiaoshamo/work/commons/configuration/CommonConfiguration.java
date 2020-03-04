package com.xiaoshamo.work.commons.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 静态文件配置
 *
 * @author lsy
 * @version 1.0.0
 */
public class CommonConfiguration {
    private final static Logger LOG = LoggerFactory.getLogger(CommonConfiguration.class);

    // 配置文件加密密钥
    private final static String AES_PWD = "nqJ#ki6po8&jv@fES5^FR4s9^XcK4p4i";

    // xml配置文件名
    private final static String XML_CONFIG_FILE = "config.xml";

    // ehcache配置文件名
    private final static String EHCACHE_CONFIGURATION_FILE = "ehcache_common.xml";

    // ehcache缓存名
    private final static String EHCACHE_CACHE_NAME = "COM_CONFIG_COLLECT";

    // 项目包名前缀
    private final static String PACKAGE_PREFIX = "com.example";
}
