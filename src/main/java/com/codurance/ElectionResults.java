package com.codurance;

public class ElectionResults {

    public String electionTransformer(String input) {

        String actualResult;

        if(input.length() < 1)
        {
            String[] results = input.split(",");
            actualResult = results[0] + " ||" + results[2] + " |" + results[1];
        }
        actualResult = input + " ||";

        return actualResult;
    }
}
