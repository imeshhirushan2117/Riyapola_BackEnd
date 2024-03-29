package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.entity.Vehicle;
import lk.riyapola.riyapola.service.CustomerService;
import lk.riyapola.riyapola.service.VehicleService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.antlr.v4.runtime.atn.TokensStartState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.controller
 * Date : Mar 8, 2024
 * Time : 1:24 AM
 */

@RestController
@RequestMapping("/riyapola/customer")
@CrossOrigin
public class CustomerController {
    final CustomerService customerService;
    final VehicleService vehicleService;
    final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public CustomerController(CustomerService customerService, JWTTokenGenerator jwtTokenGenerator, VehicleService vehicleService, VehicleService vehicleService1) {
        this.customerService = customerService;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.vehicleService = vehicleService1;
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.registerCustomer(customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PostMapping("/customerLogin")
    public ResponseEntity<Object> logInCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            HashMap<String, String> loginCustomer = customerService.loginCustomer(customerDTO);
            return new ResponseEntity<>(loginCustomer, HttpStatus.CREATED);
        }catch (Exception error) {
            return new ResponseEntity<>(error , HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Customer customer = customerService.updateCustomer(customerId, customerDTO);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Token", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/deletedCustomer/{customerId}")
    public ResponseEntity<Object> deletedCustomer(@PathVariable Long customerId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            String deleteded = customerService.deletedCustomer(customerId);
            return new ResponseEntity<>(deleteded, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Token Deleted By Customer", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/getAllVehicles")
    public ResponseEntity<Object> getAllVehicles () {
        try {
            List<Vehicle> allVehicles = customerService.getAllVehicles();
            return new ResponseEntity<>(allVehicles,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Not a Vehicles",HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/customerVeiledVehicle")
    public ResponseEntity<Object> customerLoginVehicle(@RequestHeader (name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            List<Vehicle> vehicles = customerService.customerLoginVehicle();
            return new ResponseEntity<>(vehicles , HttpStatus.CREATED);
        }
        return new ResponseEntity<>("No Vehiciles" , HttpStatus.FORBIDDEN);
    }

    @GetMapping("/getVehicleInformation/{vehicleId}")
    public ResponseEntity<Object> getVehicleById (@PathVariable Integer vehicleId , @RequestHeader (name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            List<Vehicle> vehicleById = vehicleService.getVehicleById(vehicleId);
            return new ResponseEntity<>(vehicleById , HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid Token Get By Admin", HttpStatus.FORBIDDEN);
    }


    @GetMapping("/getVehicleInformationForCustomer/{vehicleId}")
    public ResponseEntity<Object> getVehicleInformationForCustomer(@PathVariable Integer vehicleId){
      try {
          List<Vehicle> vehicleInformationForCustomer = vehicleService.getVehicleInformationForCustomer(vehicleId);
          return new ResponseEntity<>(vehicleInformationForCustomer , HttpStatus.CREATED);
      }catch (Exception e) {
          return new ResponseEntity<>("Not Vehicles Information By Customers", HttpStatus.FORBIDDEN);
      }
    }


    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long customerId, @RequestHeader (name = "Authorization")String authorizationHeader){
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            List<Customer> customerById = customerService.getCustomerById(customerId);
            return new ResponseEntity<>(customerById , HttpStatus.CREATED);
        }
        return new ResponseEntity<>("No Customer By Id" , HttpStatus.FORBIDDEN);
    }

    @GetMapping("/getAll/customers")
    public ResponseEntity<Object> getAllCustomers (@RequestHeader (name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            List<Customer> allCustomers = customerService.getAllCustomers();
            return new ResponseEntity<>(allCustomers , HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Customers ", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/getUserInfoById")
    public ResponseEntity<Object> getCustomerDetails(@RequestHeader (name = "Authorization") String authorizationHeader){
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)){
            Customer customerFromJwtToken = jwtTokenGenerator.getCustomerFromJwtToken(authorizationHeader);
            System.out.println("========= getUserInfoById " + customerFromJwtToken);

            return new ResponseEntity<>(customerFromJwtToken , HttpStatus.CREATED);

        }else{
            return new ResponseEntity<>("Token Invalid not get customer info ", HttpStatus.UNAUTHORIZED);
        }
    }


}
