package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
