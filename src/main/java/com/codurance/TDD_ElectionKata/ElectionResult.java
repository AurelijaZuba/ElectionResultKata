package com.codurance.TDD_ElectionKata;

public class ElectionResult {

    private String partyName;
    private int voteCount;

    public ElectionResult(String partyName, int voteCount) {
        this.partyName = partyName;
        this.voteCount = voteCount;
    }

    public String getPartyName()
    {
        return partyName;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
