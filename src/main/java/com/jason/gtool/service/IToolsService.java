package com.jason.gtool.service;

import com.jason.gtool.domain.req.GDoPram;
import com.jason.gtool.utils.Result;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:06
 * @desciption:
 */
public interface IToolsService {
    Result route(GDoPram param);
}
