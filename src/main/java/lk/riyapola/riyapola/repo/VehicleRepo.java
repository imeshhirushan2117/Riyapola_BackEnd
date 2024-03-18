package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.repo
 * Date : Mar 18, 2024
 * Time : 11:49 PM
 */
public interface VehicleRepo extends JpaRepository <Vehicle , Integer> {
}
