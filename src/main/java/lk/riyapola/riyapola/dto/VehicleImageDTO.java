package lk.riyapola.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.dto
 * Date : Apr 2, 2024
 * Time : 12:44 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleImageDTO {

    private MultipartFile vehicleImg;
    private String imgName;

}
