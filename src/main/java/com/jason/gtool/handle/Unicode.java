package com.jason.gtool.handle;

import cn.hutool.core.text.UnicodeUtil;
import com.jason.gtool.domain.IStrategy;
import com.jason.gtool.domain.type.Operate;
import com.jason.gtool.utils.Result;

/**
 * @author JingWei Guo
 * @date 2024/12/26 17:08
 * @desciption:
 */
public class Unicode implements IStrategy {

    public Result encrypt(String data) {
        return Result.get(200, "加密成功", UnicodeUtil.toUnicode(data));
    }

    public Result decrypt(String data) {
        return Result.get(200, "解密成功", UnicodeUtil.toString(data));
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
