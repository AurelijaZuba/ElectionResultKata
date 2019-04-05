package com.codurance.TDD_ElectionKata;

import java.util.HashMap;
import java.util.Map;

class PartyRepository {

    private static final Map<String, String> map = new HashMap<>();

    String getFullPartyName(String partyCode) throws PartyCodeNotFoundException {

        map.put("C", "Conservative Party");
        map.put("LD", "Liberal Democrats");
        map.put("L", "Labour Party");
        map.put("UKIP", "UKIP");
        map.put("G", "Green Party");
        map.put("Ind", "Independent");

        var result = map.get(partyCode);
        if (result == null) {
            throw new PartyCodeNotFoundException();
        }

        return result;
    }

}
