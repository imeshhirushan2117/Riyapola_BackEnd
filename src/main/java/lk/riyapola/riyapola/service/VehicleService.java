package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.VehicleDTO;
import lk.riyapola.riyapola.dto.VehicleImgSaveDTO;
import lk.riyapola.riyapola.entity.Vehicle;
import lk.riyapola.riyapola.entity.VehicleImg;
import lk.riyapola.riyapola.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

    public Vehicle saveVehicle(VehicleDTO vehicleDTO) throws IOException, URISyntaxException {

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

            VehicleImg  vehicleImg =  new VehicleImg();
            String absolutePath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadDir = new File(absolutePath + "/src/main/resources/static/uploads");
            uploadDir.mkdir();
            vehicleDTO.getImage().transferTo(new File(uploadDir.getAbsolutePath() + "/" +vehicleDTO.getImage().getOriginalFilename()));

            vehicleImg.setImage(absolutePath);
            vehicleImg.setImage("uploads/" +vehicleDTO.getImage().getOriginalFilename());
            vehicleImg.setVehicle(save);

            List<VehicleImg> vehicleImgs = new ArrayList<>();
            vehicleImgs.add(vehicleImg);
            save.setVehicleImgs(vehicleImgs);
            Vehicle saved = vehicleRepo.save(save);

            return saved;
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

    public List<Vehicle> getVehicleInformationForCustomer(Integer vehicleId) {
        if (vehicleRepo.existsById(vehicleId)){
            List<Vehicle> vehiclesByVehicleId = vehicleRepo.findVehiclesByVehicleId(vehicleId);
            return vehiclesByVehicleId ;
        }return null;
    }





}
