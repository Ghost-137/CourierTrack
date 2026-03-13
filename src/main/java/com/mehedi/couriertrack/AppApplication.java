package com.mehedi.couriertrack;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mehedi.couriertrack.domain.AppUser;
import com.mehedi.couriertrack.domain.AppUserRepository;
import com.mehedi.couriertrack.domain.Shift;
import com.mehedi.couriertrack.domain.ShiftRepository;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AppUserRepository appUserRepository, ShiftRepository shiftRepository) {
		return (args) -> {
			AppUser courier1 = new AppUser("Mehedi", "password123", "ROLE_COURIER");
			appUserRepository.save(courier1);

			Shift shift1 = new Shift();
			shift1.setShiftDate(LocalDate.now());
			shift1.setStartTime(LocalTime.of(17, 0));
			shift1.setEndTime(LocalTime.of(21, 30));
			shift1.setGrossEarnings(new BigDecimal("85.50"));
			shift1.setKilometersDriven(42.5);
			shift1.setAppUser(courier1);

			shiftRepository.save(shift1);
		};
	}

}
