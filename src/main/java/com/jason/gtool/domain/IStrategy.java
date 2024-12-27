package com.jason.gtool.domain;

import com.jason.gtool.domain.type.Operate;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:21
 * @desciption:
 */
public interface IStrategy {
    /**
     * 加密
     * @param data
     * @return
     */
    String encrypt(String data);

    /**
     * 解密
     * @param data
     * @return
     */
    String decrypt(String data);

    /**
     * 策略执行方法
     * @param op
     * @param data
     * @return
     */
    default String execute(Integer op, String data){
        if (Operate.DECRYPT.ordinal() ==op) {
            return this.encrypt(data);
        } else if (Operate.ENCRYPT.ordinal() ==op){
            return this.decrypt(data);
        }else {
            return data;
        }
    };
}
