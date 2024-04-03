package lk.riyapola.riyapola.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.entity
 * Date : Apr 2, 2024
 * Time : 8:19 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VehicleImg {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer imgId;
    private String image;

    @ManyToOne
    @JoinColumn (name = "vehicle_Id")
    @JsonIgnore
    private Vehicle vehicle;

}
