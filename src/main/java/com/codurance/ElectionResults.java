package com.codurance;

import java.util.*;

import static java.lang.System.*;

public class ElectionResults {


    public String electionTransformer(String input) {

        StringBuilder actualResult = new StringBuilder();
        for (var newLine : input.split(lineSeparator())) {


            String[] result = newLine.split(",");
            var constituencyName = result[0];
            actualResult.append(constituencyName);
            ArrayList<ElectionResult> electionResults = getElectionResults(result);

            ConstituencyResult constituencyResult = new ConstituencyResult(constituencyName, electionResults);

            actualResult.append(constituencyResult.toString());
            actualResult.append(lineSeparator());
        }

        var electionResult = actualResult.toString();
        var lengthWithoutSeperator = electionResult.length() - lineSeparator().length();
        return electionResult.substring(0, lengthWithoutSeperator);
    }

    private ArrayList<ElectionResult> getElectionResults(String[] result) {
        var electionResults = new ArrayList<ElectionResult>();

        for (var i = 1; i < result.length - 1; i += 2) {

            int voteCount = Integer.parseInt(result[i].trim());
            var partyCode = result[i + 1].trim();
            var partyName = getFullPartyName(partyCode);

            ElectionResult electionResult = new ElectionResult(partyName, voteCount);
            electionResults.add(electionResult);
        }
        return electionResults;
    }


    private String getFullPartyName(String partyCode) {
        Map map = new HashMap();
        map.put("C", "Conservative Party");
        map.put("LD", "Liberal Democrats");
        map.put("L", "Labour Party");
        map.put("UKIP", "UKIP");
        map.put("G", "Green Party");
        map.put("Ind", "Independent");

        return (String) map.get(partyCode);
    }


    //TODO add exception for the argument at later date for above


}
