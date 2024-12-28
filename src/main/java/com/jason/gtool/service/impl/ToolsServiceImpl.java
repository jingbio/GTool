package com.jason.gtool.service.impl;

import com.jason.gtool.domain.req.GDoPram;
import com.jason.gtool.service.IToolsService;
import com.jason.gtool.utils.Result;
import org.springframework.stereotype.Service;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:06
 * @desciption:
 */
@Service
public class ToolsServiceImpl implements IToolsService {

    @Override
    public Result route(GDoPram param) {
        return param.getRoute().getStrategy().execute(param.getOp(), param.getData());
    }
}
