Feature:
A shorthand feed with election results must be transformed into a user friendly statistical format

Scenario:
Single constituency input, to transform into user friendly format
Given Small Input
When I ask to convert the election result
Then I should get formatted statistics.

  Scenario Outline: Receiving small input of election results
    When <Input> is received
    And Converter the election results to friendly format
    And return <electionResults> as an output
  Examples:
    |Input                  | electionResults |
    |"Cardiff West, 11014, C" | "Cardiff West || Conservative Party | 100.00% " |