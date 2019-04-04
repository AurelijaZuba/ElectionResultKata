package com.codurance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PartyRepositoryShould {

    @Mock
    PartyRepository repository;
    @Mock
    ElectionResults results;

    @BeforeEach
    void setUp() {
        repository = mock(PartyRepository.class);
        results = mock(ElectionResults.class);
    }

    @Test
    void get_translated_party_name_for_one_party() {

        given(repository.getFullPartyName("C")).willReturn("Conservative Party");

        var partyName = results.electionTransformer("Cardiff West, 11014, C");

        assertThat(partyName).isEqualTo("Conservative Party");
        verify(repository).getFullPartyName("C");
    }
}
