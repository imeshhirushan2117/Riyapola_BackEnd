package lk.riyapola.riyapola.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
 * Time : 8:19 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleImgDTO {
    private int imgId;
    private MultipartFile image;
    private int vehicle_Id;
}
