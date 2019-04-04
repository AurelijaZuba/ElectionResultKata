package com.codurance;

import java.util.HashMap;
import java.util.Map;

public class PartyRepository {

    private Map map = new HashMap();

    public String getFullPartyName(String partyCode) {
        map.put("C", "Conservative Party");
        map.put("LD", "Liberal Democrats");
        map.put("L", "Labour Party");
        map.put("UKIP", "UKIP");
        map.put("G", "Green Party");
        map.put("Ind", "Independent");

        return (String) map.get(partyCode);
    }
}
