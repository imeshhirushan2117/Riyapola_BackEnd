package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;


/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.service
 * Date : Mar 7, 2024
 * Time : 11:50 PM
 */
@Service
public class AdminService {

   final AdminRepo adminRepo ;

   @Autowired
    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin saveAdmin(AdminDTO adminDTO) {
        String encodedPassword = Base64.getEncoder().encodeToString(adminDTO.getPassword().getBytes());
        Admin save = adminRepo.save(new Admin(adminDTO.getFirstName(), adminDTO.getLastName(), adminDTO.getUserName(), encodedPassword , adminDTO.getRole()));
        return save;
    }
}
