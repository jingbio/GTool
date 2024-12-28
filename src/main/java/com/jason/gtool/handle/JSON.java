package com.jason.gtool.handle;

import cn.hutool.json.JSONUtil;
import com.jason.gtool.domain.IStrategy;
import com.jason.gtool.domain.type.Operate;
import com.jason.gtool.utils.Result;

/**
 * @author JingWei Guo
 * @date 2024/12/27 09:47
 * @desciption:
 */
public class JSON implements IStrategy {

    /**
     * 格式化JSON
     * @param data
     * @return
     */
    public Result format(String data) {
        return Result.get(200, "老铁这个JSON没毛病!", JSONUtil.toJsonPrettyStr(data));
    }

    /**
     * 压缩JSON
     * @param data
     * @return
     */
    public Result density(String data) {
        return Result.get(200, "压缩成功!", JSONUtil.parse(data).toString());
    }

    @Override
    public Result execute(Integer op, String data) {
        if (Operate.DECRYPT.ordinal() ==op) {
            return this.format(data);
        } else if (Operate.ENCRYPT.ordinal() ==op){
            return this.density(data);
        }else {
            return null;
        }
    }
}
