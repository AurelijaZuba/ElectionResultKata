package com.codurance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ElectionResults {

    public String electionTransformer(String input) {

        String[] results = input.split(",");
        String constituencyName = results[0];
        var actualResult = constituencyName;

        for (var newLine : input.split(System.lineSeparator())) {
            String[] result = newLine.split(",");

            for (var i = 1; i < result.length - 1; i += 2) {

                int polingVote = Integer.parseInt(result[i].trim());
                var partyCode = result[i + 1].trim();

//                var s = IntStream.of(polingVote).sum();
//                var calculator = (double)(polingVote / s) ;


                actualResult += " || " + getFullPartyName(partyCode) + " | 100%";

            }

        }
        return actualResult;
    }


    private String voteCalculator(String polingVote) {


        return null;
    }

    private String getFullPartyName(String partyCode) {
        Map map=new HashMap();
        map.put("C", "Conservative Party");
        map.put("LD", "Liberal Democrats");
        map.put("L", "Labour Party");
        map.put("UKIP", "UKIP");
        map.put("G", "Green Party");
        map.put("Ind", "Independent");

        return (String)map.get(partyCode);
    }


    //TODO add exception for the argument at later date for above


}
