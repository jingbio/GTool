package com.jason.gtool.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author JingWei Guo
 * @date 2024/12/30 11:32
 * @desciption: 转义工具类
 */
public class EscapeUtils {
    // 私有的静态 ObjectMapper 实例
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // 私有构造方法，防止实例化
    private EscapeUtils() {
    }

    // 提供一个公共静态方法来获取 ObjectMapper 实例
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }


    /**
     * escape
     * @param data
     * @return
     */
    public static String escape(String data) {
        try {
            return OBJECT_MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * remove escape
     * @param data
     * @return
     */
    public static String unescape(String data) {
        try {
            return OBJECT_MAPPER.readValue(data, String.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
