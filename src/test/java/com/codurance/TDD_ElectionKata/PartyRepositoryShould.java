package com.codurance.TDD_ElectionKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class PartyRepositoryShould {

    private static final String PARTY_CODE = "C";

    PartyRepository repository;
    private ElectionResults electionResults;

    @BeforeEach
    void setUp() {
        repository = new PartyRepository();
        electionResults = new ElectionResults(repository);

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
