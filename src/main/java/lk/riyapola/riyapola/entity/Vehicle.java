package lk.riyapola.riyapola.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private double dailyRentalPrice;
    private String dailyLimitKilometers;
    private double extraKm;
    private String kilometersTraveled;
    private String status;

    public Vehicle(String brandName, String moduleName, int passengers, String fuelType, String transmissionType, double dailyRentalPrice, String dailyLimitKilometers, double extraKm, String kilometersTraveled, String status) {
        this.brandName = brandName;
        this.moduleName = moduleName;
        this.passengers = passengers;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.dailyRentalPrice = dailyRentalPrice;
        this.dailyLimitKilometers = dailyLimitKilometers;
        this.extraKm = extraKm;
        this.kilometersTraveled = kilometersTraveled;
        this.status = status;
    }
}
