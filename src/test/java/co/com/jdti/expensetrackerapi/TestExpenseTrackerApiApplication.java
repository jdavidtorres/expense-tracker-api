package co.com.jdti.expensetrackerapi;

import org.springframework.boot.SpringApplication;

public class TestExpenseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(ExpenseTrackerApiApplication::main)
			.with(TestcontainersConfiguration.class).run(args);
	}
}
