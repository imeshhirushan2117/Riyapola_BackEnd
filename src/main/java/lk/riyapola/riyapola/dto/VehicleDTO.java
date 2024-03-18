package lk.riyapola.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.dto
 * Date : Mar 18, 2024
 * Time : 11:50 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleDTO {
    private int vehicleId;
    private String brandName;
    private String moduleName;
    private int passengers;
    private String fuelType;
    private String transmissionType;
    private double dailyRentalPrice;
    private String dailyLimitKilometers;
    private String extraKm;
    private String kilometersTraveled;
    private String status;
}
