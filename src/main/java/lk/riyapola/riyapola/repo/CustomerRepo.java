package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.repo
 * Date : Mar 8, 2024
 * Time : 1:25 AM
 */
public interface CustomerRepo extends JpaRepository <Customer,Long>{
    List<Customer> findAllByUserName(String name);
//    @Query(value = "SELECT * FROM customer WHERE email IS NOT NULL AND contact IS NOT NULL AND nic IS NOT NULL AND address IS NOT NULL", nativeQuery = true)
//    List<Customer> findCustomersWithNonNullFields();


//    @Query(value = "SELECT * FROM customer WHERE customer_id = :customerId", nativeQuery = true)
//    List<Customer> findCustomerByCustomerId(@Param("customerId") Long customerId);

}
