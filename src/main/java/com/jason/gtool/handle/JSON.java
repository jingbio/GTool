package com.jason.gtool.handle;

import cn.hutool.json.JSONUtil;
import com.jason.gtool.domain.IStrategy;

/**
 * @author JingWei Guo
 * @date 2024/12/27 09:47
 * @desciption:
 */
public class JSON implements IStrategy {
    @Override
    public String encrypt(String data) {
        return JSONUtil.toJsonPrettyStr(data);
    }

    @Override
    public String decrypt(String data) {
        return "";
    }
}
