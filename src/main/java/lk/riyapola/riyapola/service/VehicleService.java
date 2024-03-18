package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.VehicleDTO;
import lk.riyapola.riyapola.entity.Vehicle;
import lk.riyapola.riyapola.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.service
 * Date : Mar 18, 2024
 * Time : 11:49 PM
 */
@Service
public class VehicleService {

    final VehicleRepo vehicleRepo;

    @Autowired
    public VehicleService(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    public Vehicle saveVehicle(VehicleDTO vehicleDTO) {
        if (vehicleDTO != null) {
            Vehicle save = vehicleRepo.save(new Vehicle(
                    vehicleDTO.getBrandName(),
                    vehicleDTO.getModuleName(),
                    vehicleDTO.getPassengers(),
                    vehicleDTO.getFuelType(),
                    vehicleDTO.getTransmissionType(),
                    vehicleDTO.getDailyRentalPrice(),
                    vehicleDTO.getDailyLimitKilometers(),
                    vehicleDTO.getExtraKm(),
                    vehicleDTO.getKilometersTraveled(),
                    vehicleDTO.getStatus()
            ));
            return save;
        }
        return null;
    }

    public List<Vehicle> getAllCars() {
        List<Vehicle> all = vehicleRepo.findAll();
        return all;
    }
}
