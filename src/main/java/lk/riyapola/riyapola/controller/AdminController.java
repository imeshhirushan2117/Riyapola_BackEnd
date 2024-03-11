package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> saveAdmin(@RequestBody AdminDTO adminDTO){
        Admin admin = adminService.saveAdmin(adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmin(){
        List<Admin> allAdmin = adminService.getAllAdmin();
        return new ResponseEntity<>(allAdmin,HttpStatus.OK);
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<String> deletedCustomer(@PathVariable Long adminId){
        String deleteded = adminService.deletedAdmin(adminId);
        return new ResponseEntity<>(deleteded,HttpStatus.OK);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long adminId , @RequestBody AdminDTO adminDTO){
        Admin admin = adminService.updateAdmin(adminId, adminDTO);
        return new ResponseEntity<>(admin,HttpStatus.CREATED);
    }

    @GetMapping("/searchAdminById/{adminId}")
    public ResponseEntity<Admin> searchId(@PathVariable Long adminId){
        Admin admin = adminService.findAdminById(adminId);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

    @GetMapping("searchAdminByName/{adminName}")
    public ResponseEntity<Admin> searchName(@PathVariable String adminName){
        Admin adminByName = adminService.findAdminByName(adminName);
        return new ResponseEntity<>(adminByName,HttpStatus.OK);
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<HashMap<String , String>> adminLogin(@RequestBody AdminDTO adminDTO){
        HashMap<String, String> loginAdmin = adminService.loginAdmin(adminDTO);
        return new ResponseEntity<>(loginAdmin,HttpStatus.CREATED);
    }
}
