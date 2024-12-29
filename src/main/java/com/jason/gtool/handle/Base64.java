package com.jason.gtool.handle;

import com.jason.gtool.domain.IStrategy;
import com.jason.gtool.domain.type.Operate;
import com.jason.gtool.utils.Result;
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


    public Result encrypt(String data) {
        return Result.get(200, "加密成功", cn.hutool.core.codec.Base64.encode(data));
    }


    public Result decrypt(String data) {
        return Result.get(200, "解密成功", cn.hutool.core.codec.Base64.decodeStr(data));
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.DECRYPT ==op) {
            return this.decrypt(data);
        } else if (Operate.ENCRYPT==op){
            return this.encrypt(data);
        }else {
            return null;
        }
    }
}
