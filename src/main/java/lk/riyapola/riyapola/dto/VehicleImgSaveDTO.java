package lk.riyapola.riyapola.dto;

import lk.riyapola.riyapola.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.dto
 * Date : Apr 2, 2024
 * Time : 8:26 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleImgSaveDTO {
    private int imgId;
    private String image;
    private Vehicle vehicle_Id;

    public VehicleImgSaveDTO(Integer imgId, String image, Vehicle vehicle) {
        this.imgId=imgId;
        this.image=image;
        this.vehicle_Id=vehicle;
    }

    public VehicleImgSaveDTO(Vehicle vehicle_Id) {
        this.vehicle_Id = vehicle_Id;
    }
}
