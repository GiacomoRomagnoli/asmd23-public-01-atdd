Feature:  Adding numbers with a Calculator
  In order to not learn math
  As someone who is bad at math
  I want to be able to add numbers using a Calculator

  Background:
    Given I have a Calculator

  Scenario:  Add two positive numbers
    When I add 1 and 1
    Then the sum should be 2

  Scenario:  Add a positive and negative number
    When I add 1 and -1
    Then the sum should be 0

  Scenario:  Add two negative numbers
    When I add -1 and -1
    Then the sum should be -2

  Scenario Outline: Add two numbers
    When I add <arg0> and <arg1>
    Then the sum should be <result>
    Examples:
    | arg0 | arg1 | result |
    |    3 |    2 |      5 |
    |   -1 |   -3 |     -4 |
    |   -3 |    2 |     -1 |