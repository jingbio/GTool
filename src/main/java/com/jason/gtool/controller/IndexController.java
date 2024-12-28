package com.jason.gtool.controller;

import com.jason.gtool.domain.type.RouteEnum;
import com.jason.gtool.domain.vo.Funcs;
import com.jason.gtool.domain.vo.Ops;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/27 15:19
 * @desciption:
 */
@Controller
public class IndexController {
    @PostConstruct
    private List<Ops> ops(){
        return Arrays.asList(
            new Ops("JSON校验", RouteEnum.JSON),
            new Ops("Base64", RouteEnum.BASE64),
            new Ops("Unicode转码",RouteEnum.UNICODE)
        );
    }
    private List<Funcs> funcs() {
        return Arrays.asList(
            new Funcs("格式化JSON", 0),
            new Funcs("压缩JSON", 1)
        );
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("ops", this.ops());
        model.addAttribute("funcs", this.funcs());
        return "tools";
    }
}
