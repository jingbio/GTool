package com.jason.gtool.handle;

import cn.hutool.core.text.UnicodeUtil;
import com.jason.gtool.domain.IStrategy;
import com.jason.gtool.utils.Result;

/**
 * @author JingWei Guo
 * @date 2024/12/26 17:08
 * @desciption:
 */
public class Unicode implements IStrategy {

    public String encrypt(String data) {
        return UnicodeUtil.toUnicode(data);
    }

    public String decrypt(String data) {
        return UnicodeUtil.toString(data);
    }

    @Override
    public Result execute(Integer op, String data) {
        return null;
    }
}
