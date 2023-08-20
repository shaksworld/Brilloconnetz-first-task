package com.shaks.UserRegistrationApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class ValidationResultTest {

    @Test
    public void testValidationResultWithNoErrors() {
        ValidationResult validationResult = new ValidationResult();
        assertTrue(validationResult.isValid());
        assertEquals("", validationResult.getErrorMessages());
    }

    @Test
    public void testValidationResultWithErrors() {
        ValidationResult validationResult = new ValidationResult();
        validationResult.addErrorMessage("Username", "Username cannot be empty");
        validationResult.addErrorMessage("Email", "Invalid email format");

        assertFalse(validationResult.isValid());
        String expectedErrorMessage = "Username: Username cannot be empty\n" +
                "Email: Invalid email format\n";
        assertEquals(expectedErrorMessage, validationResult.getErrorMessages());
    }
}