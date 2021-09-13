package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    public static PasswordChecker passwordChecker;

    @BeforeAll
    public static void setup() throws PasswordException{
        passwordChecker = new PasswordChecker();
    }

    @Test
    void Should_ThrowException_When_PasswordLengthIsLessThanEight() {
        assertThrows(PasswordTooShortException.class, ()-> { passwordChecker.validate("12345");});
    }

    @Test
    void Should_ThrowException_When_PasswordDoesntContainUppercaseLetter() {
        assertThrows(PasswordDoesntContainUppercaseSymbolException.class, ()-> { passwordChecker.validate("123456789");});
    }

    @Test
    void Should_ThrowException_When_PasswordDoesntContainSpecialSymbol() {
        assertThrows(PasswordDoesntContainSpecialSymbolException.class, ()-> { passwordChecker.validate("Slaptazodis123");});
    }

    @Test
    void Should_ThrowException_When_PasswordContainsSpaces() {
        assertThrows(PasswordContainsSpacesException.class, ()-> { passwordChecker.validate("Sl aptazo dis123");});
    }

    @Test
    void Should_ThrowException_When_PasswordIsNull() {
        assertThrows(PasswordIsNullException.class, ()-> { passwordChecker.validate(null);});
    }

    @Test
    void Should_Pass_When_PasswordMeetsRequirements() throws  PasswordException{
        passwordChecker.validate("Slaptazodis_123");
    }

    @Test
    void Should_Pass_When_PasswordIsLongerThanCustomMinLength()throws  PasswordException{
        int minLength = 5;
        PasswordChecker customPasswordChecker = new PasswordChecker(minLength);
        customPasswordChecker.validate("S_136");
    }

    @Test
    void Should_Pass_When_PasswordContainsCustomSpecialSymbol() throws  PasswordException{
        ArrayList<Character> specialSymbols = new ArrayList<Character>();
        specialSymbols.add('$');
        PasswordChecker customPasswordChecker = new PasswordChecker(specialSymbols);
        customPasswordChecker.validate("S$123456");
    }

    @Test
    void Should_Pass_When_PasswordContainsCustomSpecialSymbolAndIsLongerThanCustomMinLength() throws  PasswordException{
        int minLength = 5;
        ArrayList<Character> specialSymbols = new ArrayList<Character>();
        specialSymbols.add('$');
        PasswordChecker customPasswordChecker = new PasswordChecker(minLength, specialSymbols);
        customPasswordChecker.validate("S$126");
    }

    @Test
    void Should_ThrowException_When_NumberGivenToConstructorIsLessThanOne() {
        int incorrectMinLength = 0;
        assertThrows(PasswordLenghtIsLessThanOneException.class, ()-> { PasswordChecker customPasswordChecker = new PasswordChecker(incorrectMinLength);});
    }
}