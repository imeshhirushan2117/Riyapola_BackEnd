package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.repo
 * Date : Mar 8, 2024
 * Time : 1:25 AM
 */
public interface CustomerRepo extends JpaRepository <Customer,Long>{
}