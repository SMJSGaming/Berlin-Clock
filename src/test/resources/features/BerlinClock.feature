Feature: Berlin clock

  Scenario: Generating several Berlin clocks with human-readable times
    Given the provided times:
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
    And several Berlin clocks are generated for the provided times
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

    Scenario: Generating several Berlin clocks with nano second fields
      Given the provided times:
        | 00:00:00:20 |
        | 02:04:00:40 |
        | 08:23:00:01 |
        | 11:37:01:00 |
      Then building several Berlin clocks should throw IllegalArgumentException

  Scenario: Generating several Berlin clocks with text in the provided times
    Given the provided times:
      | 00:00:error   |
      | 02:error:00   |
      | error:23:00   |
      | 11:error37:01 |
    Then building several Berlin clocks should throw NumberFormatException

  Scenario: Generating several Berlin clocks with negative times
    Given the provided times:
      | 02:04:200 |
      | 08:88:88  |
      | 36:37:01  |
      | 61:61:61  |
    Then building several Berlin clocks should throw DateTimeException

  Scenario: Generating several Berlin clocks and converting the seconds
    Given the provided times:
      | 00:00:00 |
      | 23:59:59 |
    And several Berlin clocks are generated for the provided times
    When the single seconds converter is used
    Then the single seconds indicator should match the provided strings:
      | Y |
      | O |

  Scenario: Generating several Berlin clocks and converting the single minutes
    Given the provided times:
      | 00:00:00 |
      | 23:59:59 |
      | 12:32:00 |
      | 12:34:00 |
      | 12:35:00 |
    And several Berlin clocks are generated for the provided times
    When the single minutes converter is used
    Then the single minutes indicator should match the provided strings:
      | OOOO |
      | YYYY |
      | YYOO |
      | YYYY |
      | OOOO |

  Scenario: Generating several Berlin clocks and converting the 5 minutes
    Given the provided times:
      | 00:00:00 |
      | 23:59:59 |
      | 12:04:00 |
      | 12:23:00 |
      | 12:35:00 |
    And several Berlin clocks are generated for the provided times
    When the 5 minutes converter is used
    Then the 5 minutes indicator should match the provided strings:
      | OOOOOOOOOOO |
      | YYRYYRYYRYY |
      | OOOOOOOOOOO |
      | YYRYOOOOOOO |
      | YYRYYRYOOOO |

  Scenario: Generating several Berlin clocks and converting the single hours
    Given the provided times:
      | 00:00:00 |
      | 23:59:59 |
      | 02:04:00 |
      | 08:23:00 |
      | 14:35:00 |
    And several Berlin clocks are generated for the provided times
    When the single hours converter is used
    Then the single hours indicator should match the provided strings:
      | OOOO |
      | RRRO |
      | RROO |
      | RRRO |
      | RRRR |

  Scenario: Generating several Berlin clocks and converting the 5 hours
    Given the provided times:
      | 00:00:00 |
      | 23:59:59 |
      | 02:04:00 |
      | 08:23:00 |
      | 16:35:00 |
    And several Berlin clocks are generated for the provided times
    When the 5 hours converter is used
    Then the 5 hours indicator should match the provided strings:
      | OOOO |
      | RRRR |
      | OOOO |
      | ROOO |
      | RRRO |