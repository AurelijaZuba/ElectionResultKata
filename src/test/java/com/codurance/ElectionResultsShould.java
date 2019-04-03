package com.codurance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ElectionResultsShould {


    @ParameterizedTest
    @CsvSource(
           value =  "Cardiff West,;Cardiff West" , delimiter = ';'

    )
    void transform_constituency_name_to_statistical_format(String resultInput, String expectedResult) {

        ElectionResults electionResults = new ElectionResults();
        var result = electionResults.electionTransformer(resultInput);

        assertThat(result).isEqualTo(expectedResult);
    }
}
