package com.codurance;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElectionResultsShould {


    @Test
    void transform_single_result() {
        String input = "Cardiff West, 11014, C";
        ElectionResults electionResults = new ElectionResults();

        var result = electionResults.electionTransformer(input);

        assertThat(result).isEqualTo("Cardiff West || Conservative Party | 30.76% ");

    }
}
