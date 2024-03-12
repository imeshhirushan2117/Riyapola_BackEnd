package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.service
 * Date : Mar 8, 2024
 * Time : 1:24 AM
 */
@Service
public class CustomerService {
    final CustomerRepo customerRepo;
    final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, JWTTokenGenerator jwtTokenGenerator) {
        this.customerRepo = customerRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }


    public Customer registerCustomer(CustomerDTO customerDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customerDTO.getPassword());
         return  customerRepo.save(new Customer(customerDTO.getFirstName(),customerDTO.getLastName(),customerDTO.getUserName(),encodedPassword));
    }

    public HashMap<String, String> loginCustomer(CustomerDTO customerDTO) {
        HashMap<String, String> response = new HashMap<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<Customer> allByCustomerName = customerRepo.findAllByUserName(customerDTO.getUserName());

        for (Customer customer:allByCustomerName){
            boolean matches = passwordEncoder.matches(customerDTO.getPassword(), customer.getPassword());

            if (matches){
                String token = this.jwtTokenGenerator.generateJwtTokenByCustomer(customerDTO);
                response.put("token " , token);
                return response;
            }else{
                response.put("massage", "Customer Token Generate Un Success");
                return response;
            }
        }
        return response;
    }

    public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customerDTO.getPassword());
        if (customerRepo.existsById(id)){
            Customer save = customerRepo.save(new Customer(id,customerDTO.getFirstName(),customerDTO.getLastName(),customerDTO.getEmail(),customerDTO.getContact(),customerDTO.getNic(),customerDTO.getAddress(),customerDTO.getDateTime(),customerDTO.getUserName(),encodedPassword));
            return save;
        }else{
            return null;
        }
    }


}
