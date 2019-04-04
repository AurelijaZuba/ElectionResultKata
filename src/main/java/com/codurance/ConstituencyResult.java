package com.codurance;

import java.util.ArrayList;
import java.util.Formatter;

public class ConstituencyResult {
    private String constituencyName;
    private ArrayList<ElectionResult> electionResults;

    public ConstituencyResult(String constituencyName, ArrayList<ElectionResult> electionResults) {
        this.constituencyName = constituencyName;
        this.electionResults = electionResults;
    }

    @Override
    public String toString() {
        double voteSum = electionResults.stream()
                .map(x -> x.getVoteCount())
                .mapToDouble(x -> x.doubleValue())
                .sum();

        StringBuilder actualResult = new StringBuilder();
        electionResults.forEach(x -> {
            Formatter percentageFormatter = new Formatter();
            double votePercentage = ((x.getVoteCount() / voteSum) * 100);
            actualResult.append(" || " + x.getPartyName() + " | " + percentageFormatter.format("%.2f",votePercentage) + "%");
        });

        return actualResult.toString();
    }
}
