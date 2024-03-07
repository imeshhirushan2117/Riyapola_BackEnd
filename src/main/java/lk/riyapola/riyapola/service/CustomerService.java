package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

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

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    public Customer registerCustomer(CustomerDTO customerDTO) {
        String encodedPassword = Base64.getEncoder().encodeToString(customerDTO.getPassword().getBytes());
         return  customerRepo.save(new Customer(customerDTO.getFirstName(),customerDTO.getLastName(),customerDTO.getUserName(),encodedPassword));
    }
}
