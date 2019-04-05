package com.codurance.TDD_ElectionKata;

import java.util.*;

import static java.lang.System.*;

public class ElectionResults {

    private PartyRepository partyRepository;

    public ElectionResults(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public String electionTransformer(String input) {
        var electionResultBuilder = new StringBuilder();

        if(input.trim().isEmpty()) {
            throw new NullPointerException();
        }
            for (var newLine : input.split(lineSeparator())) {

                String[] constituencyInformation = newLine.split(",");
                var constituencyName = constituencyInformation[0];
                ArrayList<ElectionResult> electionResults = getElectionResults(constituencyInformation);
                var constituencyResult = new ConstituencyResult(constituencyName, electionResults);
                electionResultBuilder.append(constituencyResult.toString() + lineSeparator());
            }

            return getFormattedElectionResults(electionResultBuilder);

    }

    private String getFormattedElectionResults(StringBuilder electionResultBuilder) {
        var electionResult = electionResultBuilder.toString();
        var lengthWithoutSeperator = electionResult.length() - lineSeparator().length();
        return electionResult.substring(0, lengthWithoutSeperator);
    }

    private ArrayList<ElectionResult> getElectionResults(String[] result) {
        var electionResults = new ArrayList<ElectionResult>();

        for (var i = 1; i < result.length - 1; i += 2) {
            var voteCount = Integer.parseInt(result[i].trim());
            var partyCode = result[i + 1].trim();

            var partyName = partyRepository.getFullPartyName(partyCode);
            var electionResult = new ElectionResult(partyName, voteCount);
            electionResults.add(electionResult);
        }

        return electionResults;
    }
    //TODO add exception for the argument at later date for above
}
