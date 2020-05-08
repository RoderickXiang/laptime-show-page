package com.roderick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ManagementController {

    @GetMapping({"/authentication/login", "/authentication"})
    public String adminLogin(HttpServletRequest request, Model model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String s : parameterMap.keySet()) {
            if ("error".equals(s)) {
                model.addAttribute("error", "用户名或密码错误");
            }
        }
        return "login";
    }

    @GetMapping({"/management/mainPage","/management"})
    public String mainPage() {
        return "management/lapTimeManagement";
    }
}
