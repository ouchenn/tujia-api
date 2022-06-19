package co.tujia.tujia;

import co.tujia.tujia.controller.AuthenticationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TujiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TujiaApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("§§§§§§§§§§§§§§§§§§§§§§§ APP JUST STARTED UP §§§§§§§§§§§§§§§§§§§§§§§");
	}

}
