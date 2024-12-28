package com.jason.gtool.domain;

import com.jason.gtool.utils.Result;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:21
 * @desciption:
 */
public interface IStrategy {
    /**
     * 策略执行方法
     * @param op
     * @param data
     * @return
     */
     Result execute(Integer op, String data);
}
