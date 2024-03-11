package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.repo.AdminRepo;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
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
    final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public AdminService(AdminRepo adminRepo, JWTTokenGenerator jwtTokenGenerator) {
        this.adminRepo = adminRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public Admin saveAdmin(AdminDTO adminDTO) {
//        String encodedPassword = Base64.getEncoder().encodeToString(adminDTO.getPassword().getBytes());
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
            return "Admin Deleted Seccfull";
        }
        return "Admin Deleted Un Seccfull";
    }

    public Admin updateAdmin(Long id, AdminDTO adminDTO) {
        if (adminRepo.existsById(id)) {
            Admin update = adminRepo.save(new Admin(id, adminDTO.getFirstName(), adminDTO.getLastName(), adminDTO.getUserName(), adminDTO.getPassword(), adminDTO.getRole()));
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

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String x=passwordEncoder.encode(adminDTO.getPassword());
        //findAdminByUserNameAndPassword(username, x)

//        byte[] decodedBytes = Base64.getDecoder().decode(adminDTO.getPassword());
//        String decodedPassword = new String(decodedBytes);
//        System.out.println("decode"+decodedPassword);
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(adminDTO.getPassword());

        Admin adminByUserNameAndPassword = adminRepo.findAdminByUserNameAndPassword(adminDTO.getUserName(), encodedPassword);

        System.out.println("nipun : "+adminByUserNameAndPassword);
        System.out.println("Encode : "+encodedPassword);

        if (adminByUserNameAndPassword != null) {
            String token = this.jwtTokenGenerator.generateJwtTokenByAdmin(adminDTO);
            response.put("token ", token);
        } else {
            response.put("massage", "Token Generate Un Success");
        }
        return response;
    }


}
