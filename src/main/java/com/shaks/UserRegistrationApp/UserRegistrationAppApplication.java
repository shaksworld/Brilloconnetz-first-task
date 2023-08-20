package com.shaks.UserRegistrationApp;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class UserRegistrationAppApplication {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.print("Username: ");
		String username = scanner.nextLine();

		System.out.print("Email: ");
		String email = scanner.nextLine();

		System.out.print("Password: ");
		String password = scanner.nextLine();

		System.out.print("Date of Birth (yyyy-MM-dd): ");
		LocalDate dob = LocalDate.parse(scanner.nextLine());

		scanner.close();

		User user = new User(username, email, password, dob);

		ValidationResult validationResult = validateUser(user);
		if (validationResult.isValid()) {
			System.out.println("User registration successful.");
		} else {
			System.out.println("User registration failed. Validation errors:");
			System.out.println(validationResult.getErrorMessages());
		}
	}

	private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);


	static ValidationResult validateUser(User user) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		ValidationResult result = new ValidationResult();

		for (ConstraintViolation<User> violation : violations) {
			result.addErrorMessage(violation.getPropertyPath().toString(), violation.getMessage());
		}

		return result;
	}

	public static String generateJwtToken(String username) {
		return Jwts.builder()
				.setHeaderParam(JwsHeader.KEY_ID, "jwt-key-id")
				.setSubject(username)
				.setIssuedAt(new Date())
				.signWith(secretKey)
				.compact();
	}

	public static String verifyJwtToken(String jwtToken) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder()
					.setSigningKey(secretKey)
					.build()
					.parseClaimsJws(jwtToken);
			return "verification pass";
		} catch (Exception e) {
			return "verification fails";
		}
	}

}
