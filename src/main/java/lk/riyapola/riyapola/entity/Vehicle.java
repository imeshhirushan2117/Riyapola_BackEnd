package lk.riyapola.riyapola.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.entity
 * Date : Mar 18, 2024
 * Time : 11:50 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer vehicleId;
    private String brandName;
    private String moduleName;
    private int passengers;
    private String fuelType;
    private String transmissionType;
    private String dailyRentalPrice;
    private String dailyLimitKilometers;
    private String extraKm;
    private String status;

    @OneToMany(mappedBy = "vehicle" , cascade = CascadeType.ALL)
    List<VehicleImage> vehicleImages;


    public Vehicle(Integer vehicleId, String brandName, String moduleName, int passengers, String fuelType, String transmissionType, String dailyRentalPrice, String dailyLimitKilometers, String extraKm, String status) {
        this.vehicleId = vehicleId;
        this.brandName = brandName;
        this.moduleName = moduleName;
        this.passengers = passengers;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.dailyRentalPrice = dailyRentalPrice;
        this.dailyLimitKilometers = dailyLimitKilometers;
        this.extraKm = extraKm;
        this.status = status;
    }

    public Vehicle(String brandName, String moduleName, int passengers, String fuelType, String transmissionType, String dailyRentalPrice, String dailyLimitKilometers, String extraKm, String status) {
        this.brandName = brandName;
        this.moduleName = moduleName;
        this.passengers = passengers;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.dailyRentalPrice = dailyRentalPrice;
        this.dailyLimitKilometers = dailyLimitKilometers;
        this.extraKm = extraKm;
        this.status = status;
    }
}
