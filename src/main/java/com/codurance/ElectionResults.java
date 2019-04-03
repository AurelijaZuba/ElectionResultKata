package com.codurance;

public class ElectionResults {

    public String electionTransformer(String input) {

        String[] results = input.split(",");
        String constituencyName = results[0];

        String actualResult = constituencyName;
        for(var i = 1; i < results.length - 1; i += 2)
        {
            var polingVote = results[i];
            var partyCode = results[i + 1];

            actualResult = " ||" + getFullPartyName(partyCode) + " |" + polingVote;
        }

        return actualResult;
    }


    private String getFullPartyName(String result) {
        return null;
    }
}
