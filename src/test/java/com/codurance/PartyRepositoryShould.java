package com.codurance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PartyRepositoryShould {

    private static final String PARTY_CODE = "C";
    @Mock
    PartyRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(PartyRepository.class);
    }

    @Test
    void get_translated_party_name_for_one_party() {
        given(repository.getFullPartyName(PARTY_CODE)).willReturn("Conservative Party");

        ElectionResults results = new ElectionResults(repository);
        var electionResults = results.electionTransformer("Cardiff West, 11014, C");

        assertThat(electionResults).isEqualTo("Cardiff West || Conservative Party | 100.00%");
        verify(repository).getFullPartyName(PARTY_CODE);
    }
}
