package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.controller
 * Date : Mar 8, 2024
 * Time : 1:24 AM
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {
    final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.registerCustomer(customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PostMapping("/customerLogin")
    public ResponseEntity<HashMap<String , String>> logInCustomer(@RequestBody CustomerDTO customerDTO){
        HashMap<String, String> loginCustomer = customerService.loginCustomer(customerDTO);
        return new ResponseEntity<>(loginCustomer , HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId , @RequestBody CustomerDTO customerDTO , @RequestHeader (name = "Authorization") String authorizationHeader){
        Customer customer = customerService.updateCustomer(customerId, customerDTO, authorizationHeader);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
}
