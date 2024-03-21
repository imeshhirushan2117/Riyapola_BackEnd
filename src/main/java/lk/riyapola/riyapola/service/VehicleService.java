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

    public Vehicle updateVehicle(VehicleDTO vehicleDTO, Integer vehicleId) {

        if (vehicleRepo.existsById(vehicleId)) {
            Vehicle save = vehicleRepo.save(new Vehicle(vehicleId,
                    vehicleDTO.getBrandName(),
                    vehicleDTO.getModuleName(),
                    vehicleDTO.getPassengers(),
                    vehicleDTO.getFuelType(),
                    vehicleDTO.getTransmissionType(),
                    vehicleDTO.getDailyRentalPrice(),
                    vehicleDTO.getDailyLimitKilometers(),
                    vehicleDTO.getExtraKm(),
                    vehicleDTO.getStatus()
            ));

            return save;
        } else {
            return null;
        }
    }

    public String deletedVehicle(Integer vehicleId) {
        if (vehicleRepo.existsById(vehicleId)) {
            vehicleRepo.deleteById(vehicleId);
            return "Vehicle Deleted Successfully";
        }
        return "Vehicle Deleted Un Successfully Invalid Vehicle Id";
    }

    public List<Vehicle> getVehicleById(Integer vehicleId) {
        if (vehicleRepo.existsById(vehicleId)) {
            List<Vehicle> vehiclesByVehicleId = vehicleRepo.findVehiclesByVehicleId(vehicleId);
            return vehiclesByVehicleId;
        }
        return null;

    }
}
