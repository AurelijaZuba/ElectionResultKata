package com.codurance.TDD_ElectionKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ElectionResultsShould {

    private ElectionResults electionResults;

    @Mock
    PartyRepository repository;

    @BeforeEach
    void setUp() throws PartyCodeNotFoundException {
        repository = mock(PartyRepository.class);
        when(repository.getFullPartyName("C")).thenReturn("Conservative Party");
        when(repository.getFullPartyName("LD")).thenReturn("Liberal Democrats");
        when(repository.getFullPartyName("L")).thenReturn("Labour Party");
        when(repository.getFullPartyName("UKIP")).thenReturn("UKIP");
        when(repository.getFullPartyName("G")).thenReturn("Green Party");
        when(repository.getFullPartyName("Ind")).thenReturn("Independent");

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
    @MethodSource("invalid_input")
    void throw_an_invalid_exception_when_input_is_invalid(String invalidInput) {

        assertThrows(InvalidElectionResultException.class,
                () -> electionResults.electionTransformer(invalidInput));

    }

    static Stream<Arguments> invalid_input() {
        return Stream.of(
                arguments("Cardiff West, 11014")
        );
    }

    @ParameterizedTest
    @MethodSource("singleLineTestCases")
    void transform_constituency_name_to_statistical_format(String resultInput, String expectedResult) throws InvalidElectionResultException, PartyCodeNotFoundException {

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


    @Test
    void get_translated_party_name_for_one_party() throws InvalidElectionResultException, PartyCodeNotFoundException {
        given(repository.getFullPartyName("C")).willReturn("Conservative Party");

        ElectionResults results = new ElectionResults(repository);
        var electionResults = results.electionTransformer("Cardiff West, 11014, C");

        assertThat(electionResults).isEqualTo("Cardiff West || Conservative Party | 100.00%");
        verify(repository).getFullPartyName("C");
    }

}
