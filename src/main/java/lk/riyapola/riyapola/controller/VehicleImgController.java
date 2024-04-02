package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.service.VehicleImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.controller
 * Date : Apr 2, 2024
 * Time : 8:18 PM
 */

@RestController
@RequestMapping("/riyapola")
@CrossOrigin
public class VehicleImgController {

    final VehicleImgService vehicleImgService;

    @Autowired
    public VehicleImgController(VehicleImgService vehicleImgService) {
        this.vehicleImgService = vehicleImgService;
    }
}
