package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.service.AdminService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
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
@CrossOrigin
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

    @PostMapping("/adminLogin/login")
    public ResponseEntity<HashMap<String, String>> adminLogin(@RequestBody AdminDTO adminDTO) {

        if (adminDTO != null) {
            HashMap<String, String> loginAdmin = adminService.loginAdmin(adminDTO);
            if (loginAdmin != null && !loginAdmin.isEmpty()) {
                return new ResponseEntity<>(loginAdmin, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getAllAdmin/getAll")
    public ResponseEntity<Object> getAdmin(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Admin> allAdmin = adminService.getAllAdmin();
            return new ResponseEntity<>(allAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Token Get By Admin", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/adminDeleted/{adminId}")
    public ResponseEntity<String> deletedCustomer(@PathVariable Long adminId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            String deleted = adminService.deletedAdmin(adminId);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Token Deleted By Admin", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/updateAdmin/{adminId}")
    public ResponseEntity<Object> updateAdmin(@PathVariable Long adminId, @RequestBody AdminDTO adminDTO, @RequestHeader(name = "Authorization") String authorizationHeader) {

        Admin update = adminService.updateAdmin(adminId, adminDTO);
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            return new ResponseEntity<>(update, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid Token Update By Admin", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/getAllCustomers/customers")
    public ResponseEntity<Object> getAllCustomer(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<Customer> allCustomers = adminService.getAllCustomers();
            return new ResponseEntity<>(allCustomers, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Token get All Customers", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/searchAdminById/{adminId}")
    public ResponseEntity<Object> searchId(@PathVariable Long adminId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Admin admin = adminService.findAdminById(adminId);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Token Search Admin By Id", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/searchAdminByName/{adminName}")
    public ResponseEntity<Object> searchName(@PathVariable String adminName, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Admin adminByName = adminService.findAdminByName(adminName);
            return new ResponseEntity<>(adminByName, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Token Search Admin By Name", HttpStatus.FORBIDDEN);
    }
}
