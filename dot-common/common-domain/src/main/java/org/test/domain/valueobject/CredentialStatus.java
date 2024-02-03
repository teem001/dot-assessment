package org.test.domain.valueobject;

public enum CredentialStatus {

    ONE_TIME(false), REGULAR_PIN(false), FOUR_DIGIT(true), LOGIN(true);

    private final boolean isActive;


   CredentialStatus(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getActiveCredentials(){
        return isActive;
    }
}
