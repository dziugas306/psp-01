package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {
    public static PhoneValidator phoneValidator;

    @BeforeAll
    public static void setup() throws PhoneNumberException{
        phoneValidator = new PhoneValidator();
    }

    @Test
    void Should_ThrowException_When_PhoneNumberContainsUnsupportedSymbols() {
        assertThrows(PhoneNumberContainsUnsupportedSymbolsException.class, () -> {phoneValidator.validate("+37 $1234a67");});
    }

    @Test
    void Should_ThrowException_WhenPhoneNumberIsNull() {
        assertThrows(PhoneNumberIsNullException.class, () -> {phoneValidator.validate(null);});
    }

    @Test
    void Should_ConvertPhoneNumber_WhenOtherFormatIsGiven() throws PhoneNumberException  {
        assertEquals("+37061234567", phoneValidator.validate("861234567"));
    }

    @Test
    void Should_Pass_WhenPhoneNumberMeetsRequirements() throws PhoneNumberException {
        assertEquals("+37061234567", phoneValidator.validate("+37061234567"));
    }

    @Test
    void Should_Pass_WhenCustomValidationIsGiven() throws PhoneNumberException {
        HashMap<Integer,String> rules = new HashMap<Integer,String>();
        rules.put(12,"+371");
        PhoneValidator customPhoneValidator = new PhoneValidator(rules);
        assertEquals("+37161234567", customPhoneValidator.validate("+37161234567"));
    }

    @Test
    void Should_Throw_WhenRuleLengthIsLessThanPrefix() {
        HashMap<Integer,String> rules = new HashMap<Integer,String>();
        rules.put(2,"+371");
        assertThrows(PhoneNumberRullIncorrectException.class, () -> {PhoneValidator customPhoneValidator = new PhoneValidator(rules);});

    }
}