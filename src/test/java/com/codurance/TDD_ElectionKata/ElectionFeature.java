package com.codurance.TDD_ElectionKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElectionFeature {

    PartyRepository repository;

    @BeforeEach
    void setUp() {
        repository = new PartyRepository() {};
    }

    @Test
    void transforming_election_results_into_friendly_statistics() throws InvalidElectionResultException {

        ElectionResults electionResults = new ElectionResults(repository);

        String electionFeedResults = "Cardiff West, 11014, C, 17803, L, 4923, UKIP, 2069, LD\n" +
                "Islington South & Finsbury, 22547, L, 9389, C, 4829, LD, 3375, UKIP, 3371, G, 309, Ind";

        String expectedResults = "Cardiff West || Conservative Party | 30.76% || " +
                "Labour Party | 49.72% || " +
                "UKIP | 13.75% || " +
                "Liberal Democrats | 5.78%\n" +
                "Islington South & Finsbury || Labour Party | 51.45% " +
                "|| Conservative Party | 21.43% || " +
                "Liberal Democrats | 11.02% || " +
                "UKIP | 7.70% || Green Party | 7.69% " +
                "|| Independent | 0.71%";

        assertThat(electionResults.electionTransformer(electionFeedResults)).isEqualTo(expectedResults);
    }
}
