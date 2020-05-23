package com.roderick.controller;

import com.roderick.pojo.Vehicle;
import com.roderick.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    VehicleService vehicleService;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * 主界面
     * @return 返回主界面
     */
    @GetMapping({"/", "/index", "index.html"})
    public String infoPage() {
        return "index";
    }

    @GetMapping("/lapTime")
    public String lapTimePage(Model model) {
        List<Vehicle> vehicleList = vehicleService.listVehicle();
        model.addAttribute("vehicleList", vehicleList);
        return "lapTime";
    }

    /**
     * 获取车辆的详细的信息
     * @param id 车辆id
     */
    @GetMapping("/modelInfo")
    public String modelInfo(@RequestParam("id") int id,Model model) {
        System.out.println(id);
        // todo 获取图片和评论
        return "vehicle/modelInfo";
    }
}
