@component-test
Feature: Create alerts

  Scenario: Create valid alert
    Given the address "/logbook"
    And the endpoint "/alerts"
    And the following payload:
    """
    {
      "subsystem": "ADCS",
      "state": "NEW",
      "severity": "WARNING",
      "message": "This is a valid alert message"
    }
    """
    When a post request is performed
    Then the response code is 201
    And the body contains the following fields:
      | fieldName | value                         |
      | subsystem | ADCS                          |
      | state     | NEW                           |
      | severity  | WARNING                       |
      | message   | This is a valid alert message |

  Scenario: Create alert with missing severity field
    Given the address "/logbook"
    And the endpoint "/alerts"
    And the following payload:
    """
    {
      "subsystem": "THM",
      "state": "NEW",
      "message": "This is a valid alert message"
    }
    """
    When a post request is performed
    Then the response code is 400
    And the body contains the following fields:
      | fieldName | value                    |
      | message   | severity may not be null |

  Scenario: Create alert with invalid severity field
    Given the address "/logbook"
    And the endpoint "/alerts"
    And the following payload:
    """
    {
      "subsystem": "THM",
      "state": "NEW",
      "severity": "DEBUG",
      "message": "This is a valid alert message"
    }
    """
    When a post request is performed
    Then the response code is 400
    And the body contains the following fields:
      | fieldName | value                               |
      | message   | Malformed payload has been received |