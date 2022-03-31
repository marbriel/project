package com.library;


import com.library.services.implementation.SpecificBookService;
import com.library.services.implementation.TransactionService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
@Slf4j
@Transactional
public class ProjectApplication{
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}



}
