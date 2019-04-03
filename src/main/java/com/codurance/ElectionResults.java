package com.codurance;

import java.util.HashMap;
import java.util.Map;

public class ElectionResults {

    public String electionTransformer(String input) {

        String[] results = input.split(",");
        String constituencyName = results[0];

        String actualResult = constituencyName;
        for(var i = 1; i < results.length - 1; i += 2)
        {
            var polingVote = results[i];
            var partyCode = results[i + 1].trim();

            actualResult += " || " + getFullPartyName(partyCode) + " | 100.00%" ;
        }

        return actualResult;
    }


    private String getFullPartyName(String partyCode) {
        Map map=new HashMap();
        map.put("C", "Conservative Party");


        return (String)map.get(partyCode);
    }

    //TODO add exception for the argument at later date for above
}
