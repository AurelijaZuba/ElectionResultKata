package com.codurance.TDD_ElectionKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PartyRepositoryShould {

    private PartyRepository repository;

    @BeforeEach
    void setUp() {
        repository = new PartyRepository();
    }

    @ParameterizedTest
    @MethodSource("missing_information")
    void throw_an_exception_for_missing_party_code(String invalidInput) {
        assertThrows(PartyCodeNotFoundException.class,
                () -> repository.getFullPartyName(invalidInput));
    }
    static Stream<Arguments> missing_information() {
        return Stream.of(
                arguments("Cardiff West, 11014, Unknown")
        );
    }

}
