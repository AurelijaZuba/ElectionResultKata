package com.codurance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.System.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ElectionResultsShould {

    //Mock for repository
    //constructor inject the data within the class without making it the responsibility.
    //BDD in java


    @ParameterizedTest
    @MethodSource("singleLineTestCases")
    void transform_constituency_name_to_statistical_format(String resultInput, String expectedResult) {

        ElectionResults electionResults = new ElectionResults();
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
