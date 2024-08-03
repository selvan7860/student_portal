package com.example.student_portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentPortalApplication.class, args);

		System.out.println("**************************************");
		System.out.println("Student Portal API started successfully!");
		System.out.println("**************************************");
	}

}
