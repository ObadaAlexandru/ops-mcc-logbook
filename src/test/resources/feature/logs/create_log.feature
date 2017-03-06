@component-test
Feature: Create logs

  Scenario: Create valid log
    Given the address "/logbook"
    And the endpoint "/logs"
    And the following payload:
    """
    {
      "message": "This is a valid log message",
      "severity": "DEBUG",
      "subsystem": "THM",
      "createdOn": "2017-03-05T15:26:47",
      "downloadedOn": "2017-03-05T15:26:47"
    }
    """
    When a post request is performed
    Then the response code is 201
    And the body contains the following fields:
      | fieldName    | value                       |
      | message      | This is a valid log message |
      | severity     | DEBUG                       |
      | subsystem    | THM                         |
      | createdOn    | 2017-03-05T15:26:47         |
      | downloadedOn | 2017-03-05T15:26:47         |

  Scenario: Create log message with missing severity field
    Given the address "/logbook"
    Given the endpoint "/logs"
    And the following payload:
    """
    {
      "message": "This is a valid log message",
      "subsystem": "THM",
      "createdOn": "2017-03-05T15:26:47",
      "downloadedOn": "2017-03-05T15:26:47"
    }
    """
    When a post request is performed
    Then the response code is 400
    And the body contains the following fields:
      | fieldName | value                    |
      | message   | severity may not be null |

  Scenario: Invalid date format
    Given the address "/logbook"
    And the endpoint "/logs"
    And the following payload:
    """
    {
      "message": "This is a valid log message",
      "severity": "DEBUG",
      "subsystem": "THM",
      "createdOn": "2017/03/05T15:26:47",
      "downloadedOn": "2017/03/05T15:26:47"
    }
    """
    When a post request is performed
    Then the response code is 400
    And the body contains the following fields:
      | fieldName | value                               |
      | message   | Malformed payload has been received |

