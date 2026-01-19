package com.devops.cicd;

public class PasswordPolicy {
    public static boolean isStrong(String password){
        return (password != null &&
                isLongEnough(password) &&
                atLeastOneNumber(password) &&
                atLeastOneMaj(password) &&
                atLeastOneMin(password) &&
                atLeastOneSpecial(password));
    }

    public static boolean isLongEnough(String password){
        return password.length() > 8;
    }

    public static boolean atLeastOneNumber(String password) {
        return password.matches(".*\\d.*");
    }

    public static boolean atLeastOneMaj(String password) {
        return password.matches(".*[A-Z].*");
    }

    public static boolean atLeastOneMin(String password) {
        return password.matches(".*[a-z].*");
    }

    public static boolean atLeastOneSpecial(String password){
        return password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }
}
