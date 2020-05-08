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
     * 获取详细的信息
     * @param id 车辆id
     */
    @GetMapping("/modelInfo")
    public String modelInfo(@RequestParam("id") int id) {
        System.out.println(id);
        return "vehicle/modelInfo";
    }
}
