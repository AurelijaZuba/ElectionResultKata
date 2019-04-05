package com.codurance;

import java.util.HashMap;
import java.util.Map;

public abstract class PartyRepository {

    public static final Map<String, String> map = new HashMap<>();

     public String getFullPartyName(String partyCode) {
        map.put("C", "Conservative Party");
        map.put("LD", "Liberal Democrats");
        map.put("L", "Labour Party");
        map.put("UKIP", "UKIP");
        map.put("G", "Green Party");
        map.put("Ind", "Independent");

        return map.get(partyCode);
    }

}
