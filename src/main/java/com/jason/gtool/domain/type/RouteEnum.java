package com.jason.gtool.domain.type;

import com.jason.gtool.handle.Base64;
import com.jason.gtool.domain.IStrategy;
import com.jason.gtool.handle.JSON;
import com.jason.gtool.handle.Unicode;
import lombok.Getter;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:08
 * @desciption: 操作枚举类
 */

@Getter
public enum RouteEnum {
    UNICODE(new Unicode()),
    BASE64(new Base64()),
    JSON(new JSON());
    private final IStrategy strategy;

    RouteEnum(IStrategy strategy) {
        this.strategy = strategy;
    }

}
