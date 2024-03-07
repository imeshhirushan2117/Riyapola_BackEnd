package lk.riyapola.riyapola.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.dto
 * Date : Mar 7, 2024
 * Time : 11:49 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDTO {
    private long adminId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String role;

}
