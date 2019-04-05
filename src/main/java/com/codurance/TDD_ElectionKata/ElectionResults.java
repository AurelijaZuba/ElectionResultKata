package com.codurance.TDD_ElectionKata;

import java.util.*;

import static java.lang.System.*;

public class ElectionResults {

    private PartyRepository partyRepository;

    public ElectionResults(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public String electionTransformer(String input) throws InvalidElectionResultException, PartyCodeNotFoundException {

        if (checkInvalidInput(input)) {
            throw new NullPointerException();
        }

        var electionResultBuilder = new StringBuilder();

        for (var newLine : input.split(lineSeparator())) {
            String[] constituencyInformation = newLine.split(",");
            var constituencyName = constituencyInformation[0];
            ArrayList<ElectionResult> electionResults = getElectionResults(constituencyInformation);
            var constituencyResult = new ConstituencyResult(constituencyName, electionResults);
            electionResultBuilder.append(constituencyResult.toString() + lineSeparator());
        }

        return getFormattedElectionResults(electionResultBuilder);
    }

    private boolean checkInvalidInput(String input) {
        return input.trim().isEmpty();
    }

    private String getFormattedElectionResults(StringBuilder electionResultBuilder) {
        var electionResult = electionResultBuilder.toString();
        var lengthWithoutSeperator = electionResult.length() - lineSeparator().length();
        return electionResult.substring(0, lengthWithoutSeperator);
    }

    private ArrayList<ElectionResult> getElectionResults(String[] result) throws InvalidElectionResultException, PartyCodeNotFoundException {
        var electionResults = new ArrayList<ElectionResult>();

        if(isResultLengthInvalid(result))
        {
            throw new InvalidElectionResultException();
        }

        for (var i = 1; i <= result.length - 1; i += 2) {
            var voteCount = Integer.parseInt(result[i].trim());
            var partyCode = result[i + 1].trim();

            var partyName = partyRepository.getFullPartyName(partyCode);
            var electionResult = new ElectionResult(partyName, voteCount);
            electionResults.add(electionResult);
        }

        return electionResults;
    }

    private boolean isResultLengthInvalid(String[] result) {
        return result.length % 2 == 0;
    }
}
