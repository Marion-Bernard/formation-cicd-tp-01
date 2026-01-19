package com.devops.cicd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordPolicyTest {

    private final PasswordPolicy password = new PasswordPolicy();
    @Test
    void isStrongTest() {
        String strongPassword = "UnmdpFort!4";  // long, maj, min, chiffre, special
        String shortPassword = "faible";        // trop court
        String noNumberPassword = "Pasdechiffre!"; // pas de chiffre
        String noUpperPassword = "pasdemajuscule!4"; // pas de majuscule
        String noLowerPassword = "PASDEMINUSCULE!4"; // pas de minuscule
        String noSpecialPassword = "PasdeSPecial4"; // pas de caractère spécial

        assertTrue(PasswordPolicy.isStrong(strongPassword));

        assertFalse(PasswordPolicy.isStrong(shortPassword), "le mot de passe trop petit ne doit pas être valide");
        assertFalse(PasswordPolicy.isStrong(noNumberPassword), "un mot de passe sans nombre ne doit pas être valide");
        assertFalse(PasswordPolicy.isStrong(noUpperPassword), "un mot de passe sans majuscule ne doit pas être valide");
        assertFalse(PasswordPolicy.isStrong(noLowerPassword), "un mot de passe sans minuscule ne doit pas être valide");
        assertFalse(PasswordPolicy.isStrong(noSpecialPassword), "un mot de passe sans caractère spécial ne doit pas être valide");
    }

    @Test
    void isLongEnoughTest() {
        assertTrue(PasswordPolicy.isLongEnough("abcdefghijk"));
        assertFalse(PasswordPolicy.isLongEnough("short"));
    }

    @Test
    void atLeastOneNumberTest() {
        assertTrue(PasswordPolicy.atLeastOneNumber("abc1def"));
        assertFalse(PasswordPolicy.atLeastOneNumber("abcdef"));
    }

    @Test
    void atLeastOneMajTest() {
        assertTrue(PasswordPolicy.atLeastOneMaj("abcDef"));
        assertFalse(PasswordPolicy.atLeastOneMaj("abcdef"));
    }

    @Test
    void atLeastOneMinTest() {
        assertTrue(PasswordPolicy.atLeastOneMin("ABCdEF"));
        assertFalse(PasswordPolicy.atLeastOneMin("ABCDEF"));
    }

    @Test
    void atLeastOneSpecialTest() {
        assertTrue(PasswordPolicy.atLeastOneSpecial("abc!def"));
        assertFalse(PasswordPolicy.atLeastOneSpecial("abcdef"));
    }
}
