package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.repo.VehicleImgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.service
 * Date : Apr 2, 2024
 * Time : 8:19 PM
 */

@Service
public class VehicleImgService {

    final VehicleImgRepo vehicleImgRepo;

    @Autowired
    public VehicleImgService(VehicleImgRepo vehicleImgRepo) {
        this.vehicleImgRepo = vehicleImgRepo;
    }
}
