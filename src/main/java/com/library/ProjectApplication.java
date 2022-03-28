package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneOffset;
import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class ProjectApplication {
	private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone(ZoneOffset.UTC);
	private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

	public static void main(String[] args) {
		init();
		SpringApplication.run(ProjectApplication.class, args);
	}

	private static void init() {
		TimeZone.setDefault(DEFAULT_TIME_ZONE);
		Locale.setDefault(DEFAULT_LOCALE);
	}

}
