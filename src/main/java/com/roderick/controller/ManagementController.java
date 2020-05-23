package com.roderick.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roderick.pojo.Vehicle;
import com.roderick.service.ImageService;
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
    ImageService imageService;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
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
    public String insertVehiclePage() {
        return "management/insertVehicle";
    }

    /**
     * 添加车辆表单提交
     */
    @PostMapping("/management/insertVehicle")
    public String insertVehicle(Vehicle vehicle, @RequestParam("file") MultipartFile file) throws IOException {
        vehicleService.insertVehicle(vehicle, file);
        return "redirect:/management";  //重定向请求而不是静态资源
    }

    @GetMapping("/management/deleteVehicle")
    public String deleteVehicle(@RequestParam int id) throws IOException {
        vehicleService.deleteVehicleById(id);
        return "redirect:/management";
    }

    /**
     * 跳转修改页面（回显原数据库中的信息）
     *
     * @param id 前台传入的需要修改的id
     * @return 修改页面
     */
    @GetMapping("/management/updateVehicle")
    public String updateVehiclePage(int id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "management/updateVehicle";
    }

    /**
     * 接收修改完的表单，在数据库中进行修改
     *
     * @param vehicle 包含id的车辆对象
     * @return 重定向到管理主页
     */
    @PostMapping("/management/updateVehicle")
    public String updateVehicle(Vehicle vehicle) {
        vehicleService.updateVehicle(vehicle);
        return "redirect:/management";
    }

    @GetMapping("/management/vehicleIdentification")
    public String vehicleIdentificationPage() {
        return "management/vehicleIdentification";
    }

    @PostMapping("/management/vehicleIdentification")
    @ResponseBody
    public String vehicleIdentification(@RequestParam("file") MultipartFile file) throws IOException {
        String imagePath = imageService.addTempVehicleImage(file);  //临时存储的文件
        return imageService.vehicleIdentification(imagePath);
    }
}
