package com.jason.gtool.controller;

import com.jason.gtool.domain.req.GDoPram;
import com.jason.gtool.service.IToolsService;
import com.jason.gtool.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:04
 * @desciption:
 */

@RestController
@RequestMapping("/tools")
public class ToolsController {
    @Autowired
    IToolsService toolsService;
    @PostMapping("/execute")
    public Result execute(@RequestBody GDoPram param){
        return this.toolsService.route(param);
    }
}
