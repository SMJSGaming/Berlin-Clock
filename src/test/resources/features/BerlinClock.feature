Feature: Berlin clock

  Scenario: Generating several berlin clocks with human-readable times
    Given several Berlin clock is generated for the following times:
      | 00:00:00 |
      | 02:04:00 |
      | 08:23:00 |
      | 11:37:01 |
      | 12:04:00 |
      | 12:23:00 |
      | 12:32:00 |
      | 12:34:00 |
      | 12:35:00 |
      | 14:35:00 |
      | 16:35:00 |
      | 16:50:06 |
      | 23:59:59 |
    Then the time field should match:
      | hours | minutes | seconds |
      | 0     | 0       | 0       |
      | 2     | 4       | 0       |
      | 8     | 23      | 0       |
      | 11    | 37      | 1       |
      | 12    | 4       | 0       |
      | 12    | 23      | 0       |
      | 12    | 32      | 0       |
      | 12    | 34      | 0       |
      | 12    | 35      | 0       |
      | 14    | 35      | 0       |
      | 16    | 35      | 0       |
      | 16    | 50      | 6       |
      | 23    | 59      | 59      |

  Scenario: Generating several Berlin clocks
    Given several Berlin clock is generated for the following times:
      | 00:00:00 |
      | 23:59:59 |
      | 12:32:00 |
      | 12:34:00 |
      | 12:35:00 |
    When I use the minute converter
    Then it should be equal to the following Berlin clock minutes:
      | OOOO |
      | YYYY |
      | YYOO |
      | YYYY |
      | OOOO |