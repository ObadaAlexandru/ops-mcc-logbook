@component-test
Feature: Update alerts

  Scenario Outline: Add notes to an alert
    Given the address "/logbook"
    And the endpoint "/alerts/5/notes"
    And the following payload:
    """
    {
      "message": "<message>"
    }
    """
    When a post request is performed
    Then the response code is 200
    And the response contains the following:
      | fieldName | value   |
      | notes     | <count> |
    Examples:
      | message | count |
      | note1   | 1     |
      | note2   | 2     |
      | note3   | 3     |

  Scenario: Change alert state
    Given the address "/logbook"
    And the endpoint "/alerts/1"
    And the following payload:
    """
    {
      "newState": "ACKNOWLEDGED"
    }
    """
    When a patch request is performed
    Then the response code is 200
    And the response contains the following:
      | fieldName   | value |
      | transitions | 1     |

  Scenario Outline: Change alert to invalid state
    Given the address "/logbook"
    And the endpoint "/alerts/5"
    And the following payload:
    """
    {
      "newState": "<state>"
    }
    """
    When a patch request is performed
    Then the response code is 400
    And the body contains the following fields:
      | fieldName | value      |
      | message   | <response> |
    Examples:
      | state         | response                                                                   |
      | NEW           | [LOGBOOK-003] - Invalid state transition from ACKNOWLEDGED to NEW          |
      | ACKNOWLEDGED  | [LOGBOOK-003] - Invalid state transition from ACKNOWLEDGED to ACKNOWLEDGED |
      | INVALID_STATE | Malformed payload has been received                                        |

  Scenario Outline: Change alert assignee
    Given the address "/logbook"
    And the endpoint "/alerts/1"
    And the following payload:
    """
    {
      "newAssignee": <assignee>
    }
    """
    When a patch request is performed
    Then the response code is 200
    And the response contains the following:
      | fieldName       | value   |
      | assigneeHistory | <count> |
    Examples:
      | assignee | count |
      | 4        | 1     |
      | "5"      | 2     |
      | -2       | 3     |

  Scenario Outline: Change alert to invalid assignee
    Given the address "/logbook"
    And the endpoint "/alerts/8"
    And the following payload:
    """
    {
      "newAssignee": <assignee>
    }
    """
    When a patch request is performed
    Then the response code is 400
    And the body contains the following fields:
      | fieldName | value      |
      | message   | <response> |
    Examples:
      | assignee | response                                          |
      | 2        | [LOGBOOK-003] - Alert already assigned to user<2> |
      | "alex"   | Malformed payload has been received               |

  Scenario: Change state and assignee of an alert
    Given the address "/logbook"
    And the endpoint "/alerts/4"
    And the following payload:
    """
    {
      "newState": "RESOLVED",
      "newAssignee": 2
    }
    """
    When a patch request is performed
    Then the response code is 200
    And the response contains the following:
      | fieldName       | value |
      | transitions     | 1     |
      | assigneeHistory | 1     |

  Scenario Outline: Send invalid patch requests
    Given the address "/logbook"
    And the endpoint "/alerts/<alertId>"
    And the following payload:
    """
    {
      "newState": "<state>",
      "newAssignee": <assignee>
    }
    """
    When a patch request is performed
    Then the response code is <code>
    And the body contains the following fields:
      | fieldName | value      |
      | message   | <response> |
    Examples:
      | alertId | state         | assignee | code | response                                                                   |
      | 500     | RESOLVED      | 1        | 404  | [LOGBOOK-001] - Alert <500> not found                                      |
      | 8       | RESOLVED      | "alex"   | 400  | Malformed payload has been received                                        |
      | 8       | INVALID_STATE | "alex"   | 400  | Malformed payload has been received                                        |
      | 8       | NEW           | 1        | 400  | [LOGBOOK-003] - Invalid state transition from ACKNOWLEDGED to NEW          |
      | 8       | ACKNOWLEDGED  | 2        | 400  | [LOGBOOK-003] - Invalid state transition from ACKNOWLEDGED to ACKNOWLEDGED |
      | 8       | INVALID_STATE | 1        | 400  | Malformed payload has been received                                        |
      | 8       | RESOLVED      | 2        | 400  | [LOGBOOK-003] - Alert already assigned to user<2>                          |
