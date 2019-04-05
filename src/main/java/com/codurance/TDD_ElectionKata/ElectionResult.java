package com.codurance.TDD_ElectionKata;

class ElectionResult {

    private String partyName;
    private int voteCount;

    ElectionResult(String partyName, int voteCount) {
        this.partyName = partyName;
        this.voteCount = voteCount;
    }

    String getPartyName()
    {
        return partyName;
    }

    int getVoteCount() {
        return voteCount;
    }
}
