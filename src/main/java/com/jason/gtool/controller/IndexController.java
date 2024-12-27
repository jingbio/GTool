package com.jason.gtool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author JingWei Guo
 * @date 2024/12/27 15:19
 * @desciption:
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        return "tools";
    }
}
