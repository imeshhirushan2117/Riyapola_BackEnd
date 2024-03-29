package lk.riyapola.riyapola.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.dto.CustomerDTO;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.util
 * Date : Mar 10, 2024
 * Time : 1:55 PM
 */


@Component
public class JWTTokenGenerator {

    final CustomerRepo customerRepo;

    @Value("${riyapola.app.jwtSecret}")
    private String jwtSecret;

    @Value("${riyapola.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    public JWTTokenGenerator(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    public String generateJwtTokenByAdmin(AdminDTO adminDTO) {
        return Jwts.builder()
                .setId(String.valueOf(adminDTO.getAdminId()))
                .setSubject(adminDTO.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String generateJwtTokenByCustomer(CustomerDTO customerDTO) {
        return Jwts.builder()
                .setId(String.valueOf(customerDTO.getCustomerId()))
                .setSubject(customerDTO.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    public boolean validateJwtToken(String authToken) {
        String jwtToken = authToken.substring("Bearer ".length());

        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(jwtToken);
            return true;
        } catch (Exception e) {
            System.out.println("Error when generating token...");
        }
        return false;
    }

    public Customer getCustomerFromJwtToken(String token) {
        String jwtToken = token.substring("Bearer ".length());
        System.out.println("Token ====== " + jwtToken);

        String id = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(jwtToken).getBody().getId();
        Long customerId = Long.parseLong(id);
        System.out.println("customerId ====== "+ id);

        return customerRepo.getCustomerByCustomerId(customerId);
    }

}
