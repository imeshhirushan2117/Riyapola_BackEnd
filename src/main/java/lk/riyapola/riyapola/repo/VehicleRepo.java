package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.repo
 * Date : Mar 18, 2024
 * Time : 11:49 PM
 */
public interface VehicleRepo extends JpaRepository <Vehicle , Integer> {

    @Query(value = "SELECT * FROM customer WHERE customer_id = :customerId", nativeQuery = true)
    List<Customer> findCustomerByCustomerId(@Param("customerId") Long customerId);
}
