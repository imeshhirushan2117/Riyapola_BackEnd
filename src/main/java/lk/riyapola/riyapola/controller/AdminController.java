package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.service.AdminService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
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
 * Date : Mar 7, 2024
 * Time : 11:48 PM
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    final AdminService adminService;
    final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public AdminController(AdminService adminService, JWTTokenGenerator jwtTokenGenerator) {
        this.adminService = adminService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<Admin> saveAdmin(@RequestBody AdminDTO adminDTO) {
        Admin admin = adminService.saveAdmin(adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @GetMapping("/getAllAdmin/getAll")
    public ResponseEntity<Object> getAdmin(@RequestHeader(name = "Authorization") String authorization) {
        if (jwtTokenGenerator.validateJwtToken(authorization)) {
            List<Admin> allAdmin = adminService.getAllAdmin();
            return new ResponseEntity<>(allAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Token Get Admin", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/adminDeleted/{adminId}")
    public ResponseEntity<String> deletedCustomer(@PathVariable Long adminId, @RequestHeader(name = "Authorization") String authorization) {
        if (jwtTokenGenerator.validateJwtToken(authorization)) {
            String deleted = adminService.deletedAdmin(adminId);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Token Deleted Admin", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/updateAdmin/{adminId}")
    public ResponseEntity<Object> updateAdmin(@PathVariable Long adminId, @RequestBody AdminDTO adminDTO, @RequestHeader(name = "Authorization") String authorization) {

        Admin update = adminService.updateAdmin(adminId, adminDTO);
        if (jwtTokenGenerator.validateJwtToken(authorization)) {
            return new ResponseEntity<>(update, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid Token Update Admin", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/searchAdminById/{adminId}")
    public ResponseEntity<Admin> searchId(@PathVariable Long adminId) {
        Admin admin = adminService.findAdminById(adminId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/searchAdminByName/{adminName}")
    public ResponseEntity<Admin> searchName(@PathVariable String adminName) {
        Admin adminByName = adminService.findAdminByName(adminName);
        return new ResponseEntity<>(adminByName, HttpStatus.OK);
    }

    @PostMapping("/adminLogin/login")
    public ResponseEntity<HashMap<String, String>> adminLogin(@RequestBody AdminDTO adminDTO) {
        HashMap<String, String> loginAdmin = adminService.loginAdmin(adminDTO);
        return new ResponseEntity<>(loginAdmin, HttpStatus.CREATED);
    }


    @GetMapping("/getAllCustomers")
    public ResponseEntity<Object> getAllCustomer(@RequestHeader(name = "Authorization") String authorization) {
        if (jwtTokenGenerator.validateJwtToken(authorization)) {
            List<Customer> allCustomers = adminService.getAllCustomers();
            if (allCustomers != null) {
                return new ResponseEntity<>(allCustomers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Customers", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Invalid Token", HttpStatus.FORBIDDEN);
    }

}
