package com.codurance;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElectionResultsShould {


    @Test
    void transform_constituency_name_to_statistical_format() {
        String resultInput = "Cardiff West";

        ElectionResults electionResults = new ElectionResults();
        var result = electionResults.electionTransformer(resultInput);

        assertThat(result).isEqualTo("Cardiff West");
    }



}
