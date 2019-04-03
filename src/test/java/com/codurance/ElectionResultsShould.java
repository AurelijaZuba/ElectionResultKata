package com.codurance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ElectionResultsShould {

    @ParameterizedTest
    @MethodSource("singleLineTestCases")
    void transform_constituency_name_to_statistical_format(String resultInput, String expectedResult) {

        ElectionResults electionResults = new ElectionResults();
        var result = electionResults.electionTransformer(resultInput);

        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> singleLineTestCases() {
        return Stream.of(
                arguments("Cardiff West,", "Cardiff West"),
                arguments("Cardiff West, 11014, C", "Cardiff West || Conservative Party | 100.00%")
        );
    }
}
