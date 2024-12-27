package com.jason.gtool.handle;

import cn.hutool.core.text.UnicodeUtil;
import com.jason.gtool.domain.IStrategy;

/**
 * @author JingWei Guo
 * @date 2024/12/26 17:08
 * @desciption:
 */
public class Unicode implements IStrategy {
    @Override
    public String encrypt(String data) {
        return UnicodeUtil.toUnicode(data);
    }

    @Override
    public String decrypt(String data) {
        return UnicodeUtil.toString(data);
    }
}
