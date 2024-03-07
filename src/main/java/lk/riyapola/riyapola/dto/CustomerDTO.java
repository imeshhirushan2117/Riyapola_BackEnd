package lk.riyapola.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.dto
 * Date : Mar 8, 2024
 * Time : 1:23 AM
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String nic;
    private String address;
    private String dateTime;
    private String userName;
    private String password;

}
