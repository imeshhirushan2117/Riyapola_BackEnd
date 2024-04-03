package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.entity.Vehicle;
import lk.riyapola.riyapola.entity.VehicleImg;
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

    @Query(value = "SELECT * FROM vehicle WHERE vehicle_id = :vehicleId", nativeQuery = true)
    List<Vehicle> findVehiclesByVehicleId (@Param("vehicleId") Integer vehicleId);



}
