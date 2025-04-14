package lk.iit.guardianchain.guardianchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GuardianchainApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuardianchainApplication.class, args);
	}

}
