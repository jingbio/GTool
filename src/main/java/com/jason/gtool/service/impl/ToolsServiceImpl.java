package com.jason.gtool.service.impl;

import cn.hutool.core.util.IdUtil;
import com.jason.gtool.domain.req.GDoPram;
import com.jason.gtool.domain.req.SharePram;
import com.jason.gtool.domain.type.RouteEnum;
import com.jason.gtool.domain.vo.Op;
import com.jason.gtool.domain.vo.Route;
import com.jason.gtool.service.IToolsService;
import com.jason.gtool.common.utils.Result;
import com.jason.gtool.common.utils.ShareCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:06
 * @desciption: 对于固定的路由取缓存，避免了重复创建对象操作。
 */
@Service
public class ToolsServiceImpl implements IToolsService {
    @Value("${gtool.host}")
    private String host;
    @Autowired
    private ShareCache shareCache;
    @Override
    public Result route(GDoPram param) {
        return param.getRoute().getStrategy().execute(param.getOp(), param.getData());
    }

    @Override
    @Cacheable(cacheNames = "routes")
    public Result getRoutes() {
        return Result.get(200, "success",Route.routes());
    }

    @Override
    @Cacheable(cacheNames = "routeOptions", key = "#param")
    public Result getReouteOptions(RouteEnum param) {
        return Result.get(200, "success", Op.getOpsByRoute(param));
    }

    @Override
    public Result share(SharePram param) {
        String sid = IdUtil.fastSimpleUUID();
        this.shareCache.set(sid, param);
        return Result.get(200, "复制成功! 5 分钟后过期", host+"/share/"+sid);
    }
}
