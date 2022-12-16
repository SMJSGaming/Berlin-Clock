Feature: Berlin

  Scenario: Generating several berlin clocks
    Given a berlin clock is generated for the following times:
      | 00:00:00 |
      | 23:59:59 |
      | 12:32:00 |
      | 12:34:00 |
      | 12:35:00 |
    When I use the minute converter
    Then it should be equal to the following berlin clock minutes:
      | OOOO |
      | YYYY |
      | YYOO |
      | YYYY |
      | OOOO |