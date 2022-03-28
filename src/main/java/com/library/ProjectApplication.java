package com.library;

import com.library.models.Book;
import com.library.models.Borrower;
import com.library.models.SpecificBook;
import com.library.models.Transaction;
import com.library.repositories.BookRepository;
import com.library.repositories.BorrowerRepository;
import com.library.repositories.SpecificBookRepository;
import com.library.repositories.TransactionRepository;
import com.library.services.implementation.BorrowerService;
import com.library.services.implementation.SpecificBookService;
import com.library.utils.BorrowerNotFoundException;
import com.library.utils.StringManipulator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.ZoneOffset;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

@SpringBootApplication
@Slf4j

public class ProjectApplication{
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BorrowerService service){
		return args -> {
			service.deleteBorrowerById(1L);
			service.retrieveAllBorrower()
					.ifPresent(borrowers -> borrowers.forEach(System.out::println));
		};
	}
}
