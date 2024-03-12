package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.AdminRepo;
import lk.riyapola.riyapola.repo.CustomerRepo;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.service
 * Date : Mar 7, 2024
 * Time : 11:50 PM
 */
@Service
public class AdminService {

    final AdminRepo adminRepo;
    final CustomerRepo customerRepo;
    final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public AdminService(AdminRepo adminRepo, CustomerRepo customerRepo, JWTTokenGenerator jwtTokenGenerator) {
        this.adminRepo = adminRepo;
        this.customerRepo = customerRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public Admin saveAdmin(AdminDTO adminDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(adminDTO.getPassword());

        Admin save = adminRepo.save(new Admin(adminDTO.getFirstName(), adminDTO.getLastName(), adminDTO.getUserName(), encodedPassword, adminDTO.getRole()));
        return save;
    }

    public List<Admin> getAllAdmin() {
        return adminRepo.findAll();
    }

    public String deletedAdmin(Long id) {
        if (adminRepo.existsById(id)) {
            adminRepo.deleteById(id);
            return "Admin Deleted Successfully";
        }
        return "Admin Deleted Un Successfully";
    }

    public Admin updateAdmin(Long id, AdminDTO adminDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(adminDTO.getPassword());

        if (adminRepo.existsById(id)) {
            Admin update = adminRepo.save(new Admin(id, adminDTO.getFirstName(), adminDTO.getLastName(), adminDTO.getUserName(), encodedPassword, adminDTO.getRole()));
            return update;
        }
        return null;
    }

    public Admin findAdminById(Long id) {
        Optional<Admin> byId = adminRepo.findById(id);
        return byId.orElse(null);
    }

    public Admin findAdminByName(String adminName) {
        return adminRepo.findByFirstName(adminName);
    }

    public HashMap<String, String> loginAdmin(AdminDTO adminDTO) {
        HashMap<String, String> response = new HashMap<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<Admin> allAdmins = adminRepo.findAllByUserName(adminDTO.getUserName());

        for (Admin admin : allAdmins) {
            boolean matches = passwordEncoder.matches(adminDTO.getPassword(), admin.getPassword());
            if (matches) {
                String token = this.jwtTokenGenerator.generateJwtTokenByAdmin(adminDTO);
                response.put("token ", token);
                return response;
            } else {
                response.put("massage", "Admin Token Generate Un Success");
                return response;
            }
        }
        return response;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> all = customerRepo.findAll();
        return all;
    }

}
