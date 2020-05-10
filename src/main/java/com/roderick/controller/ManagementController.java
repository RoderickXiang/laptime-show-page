package com.roderick.controller;

import com.roderick.pojo.Vehicle;
import com.roderick.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ManagementController {
    VehicleService vehicleService;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

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

    @GetMapping({"/management/mainPage", "/management"})
    public String mainPage(Model model) {
        List<Vehicle> vehicleList = vehicleService.listVehicle();
        model.addAttribute("vehicleList", vehicleList);
        return "management/lapTimeManagement";
    }

    @GetMapping("/management/insertVehicle")
    public String insertVehicle(Model model) {
        // todo 添加
        return "management/insertVehicle";
    }

    @GetMapping("/management/deleteVehicle")
    public String deleteVehicle(@RequestParam int id) {
        // todo 删除
        vehicleService.deleteVehicleById(id);
        return "redirect:/management";
    }

    @GetMapping("/management/updateVehicle")
    public String updateVehicle(Model model) {
        // todo 修改
        return "management/updateVehicle";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String test(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        vehicleService.addVehicleImage(file, request);
        return "ok";
    }
}
