package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {
    public static EmailValidator emailValidator;

    @BeforeAll
    public static void setup(){
        emailValidator = new EmailValidator();
    }

    @Test
    void Should_ThrowException_When_EmailDoesntContainEta() {
        assertThrows(EmailDoesntContainEtaException.class, () ->{emailValidator.validate("emailgmail.com");});
    }

    @Test
    void Should_ThrowException_When_EmailUsernameLengthIsZero() {
        assertThrows(EmailUsernameException.class, () ->{emailValidator.validate("@gmail.com");});
    }

    @Test
    void Should_ThrowException_When_EmailUserNameContainsSpaces() {
        assertThrows(EmailUsernameException.class, () ->{emailValidator.validate("user name@gmail.com");});
    }

    @Test
    void Should_ThrowException_When_EmailDomainStartsWithSpecialCharacter() {
        assertThrows(EmailDomainException.class, () ->{emailValidator.validate("username@_gmail.com");});
    }

    @Test
    void Should_ThrowException_When_EmailDomainEndsWithSpecialCharacter() {
        assertThrows(EmailDomainException.class, () ->{emailValidator.validate("username@gmail_.com");});
    }

    @Test
    void Should_ThrowException_When_EmailDomainContainsSpace() {
        assertThrows(EmailDomainException.class, () ->{emailValidator.validate("username@gm ail.com");});
    }

    @Test
    void Should_ThrowException_When_EmailDomainIsEmpty() {
        assertThrows(EmailDomainException.class, () ->{emailValidator.validate("username@.com");});
    }

    @Test
    void Should_ThrowException_WhenTLDLengthIsLessThanTwo() {
        assertThrows(EmailTLDException.class, () ->{emailValidator.validate("username@gmail.a");});
    }

    @Test
    void Should_ThrowException_When_EmailDTLDEndsWithDash() {
        assertThrows(EmailTLDException.class, () ->{emailValidator.validate("username@gmail.com-");});
    }

    @Test
    void Should_ThrowException_When_EmailTLDStartsWithDash() {
        assertThrows(EmailTLDException.class, () ->{emailValidator.validate("username@gmail.com-");});
    }

    @Test
    void Should_ThrowException_When_EmailTLDIsEmpty() {\
        assertThrows(EmailTLDException.class, () ->{emailValidator.validate("username@gmail.");});
    }

    @Test
    void Should_Pass_When_EmailMeetsRequirments() throws EmailException {
        emailValidator.validate("user.name@domain-special.tld-com");
    }
}