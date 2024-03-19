package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.VehicleDTO;
import lk.riyapola.riyapola.entity.Vehicle;
import lk.riyapola.riyapola.service.VehicleService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.controller
 * Date : Mar 18, 2024
 * Time : 11:48 PM
 */
@RestController
@CrossOrigin
@RequestMapping("admin/vehicle")
public class VehicleRegisterController {

    final VehicleService vehicleService;
    final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public VehicleRegisterController(VehicleService vehicleService, JWTTokenGenerator jwtTokenGenerator) {
        this.vehicleService = vehicleService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/saveVehicle")
    public ResponseEntity<Object> saveVehicle(@RequestBody VehicleDTO vehicleDTO, @RequestHeader(name = "Authorization") String authorizationHeader) {

        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Vehicle vehicleSave = vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>(vehicleSave, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid Token Get By Admin", HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/getAllVehicles/vehicles")
    public ResponseEntity<Object> getAllCars(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Vehicle> allCars = vehicleService.getAllCars();
            return new ResponseEntity<>(allCars, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Token Get By Admin", HttpStatus.FORBIDDEN);
    }

    @PutMapping("/updateVehicle/{vehicleId}")
    public ResponseEntity<Object> updateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable Integer vehicleId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Vehicle vehicleUpdate = vehicleService.updateVehicle(vehicleDTO, vehicleId);
            return new ResponseEntity<>(vehicleUpdate, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid Token Get By Admin", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/deletedVehicle/{vehicleId}")
    public ResponseEntity<Object> deletedVehicle(@PathVariable Integer vehicleId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            String deleted = vehicleService.deletedVehicle(vehicleId);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Token Get By Admin" , HttpStatus.FORBIDDEN);
        }

    }
}
