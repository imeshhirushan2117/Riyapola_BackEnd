package lk.riyapola.riyapola.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.entity
 * Date : Mar 7, 2024
 * Time : 11:30 PM
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String nic;
    private String address;
    private String dateTime;
    private String userName;
    private String password;

    public Customer(String firstName, String lastName, String userName, String secondDateAsString, String encodedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateTime = secondDateAsString;
        this.userName = userName;
        this.password = encodedPassword;
    }
}
