package Hybrid.E_Pharmacy.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "entity")
public class HybridEPharmacyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HybridEPharmacyProjectApplication.class, args);
	}

}
