package com.jason.gtool.controller;

import com.jason.gtool.domain.type.RouteEnum;
import com.jason.gtool.service.IToolsService;
import com.jason.gtool.utils.ShareCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author JingWei Guo
 * @date 2024/12/27 15:19
 * @desciption:
 */
@Controller
public class IndexController {
    @Autowired
    IToolsService toolsService;
    @Autowired
    ShareCache shareCache;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("routes", this.toolsService.getRoutes().getData());
        model.addAttribute("ops", this.toolsService.getReouteOptions(RouteEnum.JSON).getData());
        return "tools";
    }
    @GetMapping("/share/{sid}")
    public String share(@PathVariable("sid") String sid, Model model) {
        model.addAttribute("routes", this.toolsService.getRoutes().getData());
        model.addAttribute("ops", this.toolsService.getReouteOptions(RouteEnum.JSON).getData());
        model.addAttribute("share", this.shareCache.get(sid));
        return "tools";
    }

}
