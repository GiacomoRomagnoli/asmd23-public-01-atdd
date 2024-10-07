Feature: OOP exam
  In order to pass the exam the application should be designed accordingly to the requirements

  Background:
    Given I start the application

  Scenario: Appearance
    Then I see a button grid in which every button belonging to an odd row or an odd column is marked with a *

  Scenario Outline: Click a * button
    When I click the button at row <x> and column <y>
    Then nothing happens
    Examples:
      | x | y |
      | 0 | 1 |
      | 1 | 0 |
      | 1 | 1 |

  Scenario Outline: Click a not marked button
    When I click the button at row <x> and column <y>
    Then I see an o appearing on the button at row <x> and column <y>
    Examples:
      | x | y |
      | 0 | 0 |
      | 0 | 2 |
      | 2 | 0 |

  Scenario Outline: Game over
    When I click the button at row <x1> and column <y1>
    And I click the button at row <x2> and column <y2>
    And I click the button at row <x3> and column <y3>
    And I click the button at row <x4> and column <y4>
    Then a minimal square is formed and the game is over
    Examples:
      | x1 | y1 | x2 | y2 | x3 | y3 | x4 | y4 |
      |  0 |  0 |  0 |  2 |  2 |  0 |  2 |  2 |

  Scenario Outline: Only last 4 clicks matters for game over
    When I click the button at row <x0> and column <y0>
    And I click the button at row <x1> and column <y1>
    And I click the button at row <x2> and column <y2>
    And I click the button at row <x3> and column <y3>
    And I click the button at row <x4> and column <y4>
    Then a minimal square is formed and the game is over
    Examples:
      | x0 | y0 | x1 | y1 | x2 | y2 | x3 | y3 | x4 | y4 |
      |  1 |  1 |  0 |  0 |  0 |  2 |  2 |  0 |  2 |  2 |
      |  4 |  4 |  0 |  0 |  0 |  2 |  2 |  0 |  2 |  2 |