package lk.riyapola.riyapola;

import jakarta.annotation.PostConstruct;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.logging.ErrorManager;
import java.util.stream.Stream;

@SpringBootApplication
public class RiyapolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiyapolaApplication.class, args);
	}

	final AdminRepo adminRepo;
	@Autowired
	public RiyapolaApplication(AdminRepo adminRepo) {
		this.adminRepo = adminRepo;
	}

	@PostConstruct
	public void initUsers() {
		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode("12345");
		List<Admin> admin = Stream.of(
				new Admin("Imesh", "Hirushan", "imesh@gmail.com", encodedPassword , "Admin")
		).toList();
		adminRepo.saveAll(admin);*/
	}

}
