package lk.riyapola.riyapola.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.entity.Vehicle;
import lk.riyapola.riyapola.repo.CustomerRepo;
import lk.riyapola.riyapola.repo.VehicleRepo;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    final VehicleRepo vehicleRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, JWTTokenGenerator jwtTokenGenerator, VehicleRepo vehicleRepo) {
        this.customerRepo = customerRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.vehicleRepo = vehicleRepo;
    }


    public Customer registerCustomer(CustomerDTO customerDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customerDTO.getPassword());

        LocalDateTime dateTime = LocalDateTime.now();
        String currentDateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        customerDTO.setDateTime(currentDateTimeString);
        System.out.println("Date & Time : " + customerDTO.getDateTime());

        return customerRepo.save(new Customer(
                        customerDTO.getFirstName(),
                        customerDTO.getLastName(),
                        customerDTO.getUserName(),
                        dateTime,
                        encodedPassword
                )
        );
    }

    public HashMap<String, String> loginCustomer(CustomerDTO customerDTO) {
        HashMap<String, String> response = new HashMap<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<Customer> allByCustomerName = customerRepo.findAllByUserName(customerDTO.getUserName());

        for (Customer customer : allByCustomerName) {
            boolean matches = passwordEncoder.matches(customerDTO.getPassword(), customer.getPassword());
            if (matches) {
                String token = this.jwtTokenGenerator.generateJwtTokenByCustomer(customerDTO);
                response.put("token", token);
                return response;
            } else {
                response.put("massage : ", "Customer Token Generate Un Success");
            }
        }
        return response;
    }

    public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customerDTO.getPassword());

        LocalDateTime dateTime = LocalDateTime.now();
        String currentDateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        customerDTO.setDateTime(currentDateTimeString);


        if (customerRepo.existsById(id)) {
            Customer save = customerRepo.save(new Customer(id,
                    customerDTO.getFirstName(),
                    customerDTO.getLastName(),
                    customerDTO.getEmail(),
                    customerDTO.getContact(),
                    customerDTO.getNic(),
                    customerDTO.getAddress(),
                    customerDTO.getDateTime(),
                    customerDTO.getUserName(),
                    encodedPassword));
            return save;
        } else {
            return null;
        }
    }

    public String deletedCustomer(Long customerId) {
        if (customerRepo.existsById(customerId)) {
            customerRepo.deleteById(customerId);
            return "Customer Deleted Successfully";
        } else {
            return "Customer Deleted Un Successfully";
        }

    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> cars = vehicleRepo.findAll();
        return cars;
    }

    public List<Vehicle> customerLoginVehicle() {
        return vehicleRepo.findAll();
    }

    public List<Customer> getCustomerById(Long customerId) {
        if (customerRepo.existsById(customerId)){
            List<Customer> customerByCustomerId = customerRepo.findCustomerByCustomerId(customerId);
            return customerByCustomerId;
        }else{
            return null;
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> all = customerRepo.findAll();
        return all;
    }

}
