package com.jason.gtool.handle;

import com.jason.gtool.domain.IStrategy;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*   @author JingWei Guo
*   @date 2024/12/26 15:15
*   @desciption: base64操作
*/
@Data
@NoArgsConstructor
public class Base64 implements IStrategy {

    @Override
    public String encrypt(String data) {
        return cn.hutool.core.codec.Base64.encode(data);
    }

    @Override
    public String decrypt(String data) {
        return cn.hutool.core.codec.Base64.decodeStr(data);
    }
}
