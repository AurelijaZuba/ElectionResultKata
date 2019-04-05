package com.codurance.TDD_ElectionKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ElectionResultsShould {

    private PartyRepository repository;
    private ElectionResults electionResults;

    @BeforeEach
    void setUp() {

        repository = new PartyRepository() {};
        electionResults = new ElectionResults(repository);
    }

    @ParameterizedTest
    @MethodSource("invalid_election_input")
    void throw_an_exception_for_invalid_input(String invalidInput) {

        assertThrows(NullPointerException.class,
                () -> electionResults.electionTransformer(invalidInput));
    }
    static Stream<Arguments> invalid_election_input() {
        return Stream.of(
                arguments(" "),
                arguments(""),
                arguments((Object) null)
        );
    }

    @ParameterizedTest
    @MethodSource("missing_information")
    void throw_an_exception_for_missing_party_code(String invalidInput) {

        assertThrows(InvalidElectionResultException.class,
                () -> electionResults.electionTransformer(invalidInput));
    }
    static Stream<Arguments> missing_information() {
        return Stream.of(
                arguments("Cardiff West, 11014")
        );
    }


    @ParameterizedTest
    @MethodSource("singleLineTestCases")
    void transform_constituency_name_to_statistical_format(String resultInput, String expectedResult) throws InvalidElectionResultException {

        var result = electionResults.electionTransformer(resultInput);

        assertThat(result).isEqualTo(expectedResult);
    }
    static Stream<Arguments> singleLineTestCases() {
        return Stream.of(
                arguments("Cardiff West, 11014, C", "Cardiff West || Conservative Party | 100.00%"),
                arguments("Cardiff West, 11014, C, 17803, L, 4923, UKIP, 2069, LD"
                                + lineSeparator() +
                                "Islington South & Finsbury, 22547, L, 9389, C, 4829, LD, 3375, UKIP, 3371, G, 309, Ind",
                        "Cardiff West || Conservative Party | 30.76% || Labour Party | 49.72% || UKIP | 13.75% || Liberal Democrats | 5.78%"
                                + lineSeparator() +
                                "Islington South & Finsbury || Labour Party | 51.45% || Conservative Party | 21.43% || Liberal Democrats | 11.02% || " +
                                "UKIP | 7.70% || Green Party | 7.69% || Independent | 0.71%")
        );
    }

}
