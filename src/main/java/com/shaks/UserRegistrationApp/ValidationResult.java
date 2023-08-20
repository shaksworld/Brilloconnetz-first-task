package com.shaks.UserRegistrationApp;

public class ValidationResult {

    private StringBuilder errorMessages = new StringBuilder();

    public void addErrorMessage(String field, String message) {
        errorMessages.append(field).append(": ").append(message).append("\n");
    }

    public boolean isValid() {
        return errorMessages.length() == 0;
    }

    public String getErrorMessages() {
        return errorMessages.toString();
    }
}
