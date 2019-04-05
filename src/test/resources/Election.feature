Feature:
A shorthand feed with election results must be transformed into a user friendly statistical format

Scenario:
Single constituency input, to transform into user friendly format
Given Small Input
When I ask to convert the election result
Then I should get formatted statistics