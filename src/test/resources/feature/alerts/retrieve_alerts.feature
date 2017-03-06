@component-test
Feature: Get individual alerts and search by property

  Scenario: Get alert by id
    Given the address "/logbook"
    And the endpoint "/alerts/2"
    When a get request is performed
    Then the response code is 200
    And the body contains the following fields:
      | fieldName | value               |
      | alertId   | 2                   |
      | assignee  | 2                   |
      | subsystem | THM                 |
      | severity  | CRITICAL            |
      | message   | test message2       |
      | state     | ACKNOWLEDGED        |
      | createdOn | 2015-01-05T00:10:00 |

  Scenario Outline: Get alerts by property
    Given the address "/logbook"
    And the endpoint "/alerts?<property>=<value>"
    When a get request is performed
    Then the response code is 200
    And the response contains <items> items
    Examples:
      | property    | value        | items |
      | severity    | CRITICAL     | 2     |
      | subsystemId | EPS          | 1     |
      | state       | ACKNOWLEDGED | 3     |
      | assigneeId  | 2            | 2     |


  Scenario Outline: Get alerts by date
    Given the address "/logbook"
    And the endpoint "/alerts?since=<since>&until=<until>"
    When a get request is performed
    Then the response code is 200
    And the response contains <items> items
    Examples:
      | since               | until               | items |
      | 2015-01-01T00:00:00 | 2015-04-01T00:00:00 | 4     |
      | 2015-04-01T00:00:00 | 2015-09-01T00:00:00 | 2     |
      | 2015-01-01T00:00:00 | 2015-09-01T00:00:00 | 6     |
      | 2016-04-01T00:00:00 | 2016-09-01T00:00:00 | 0     |

  Scenario Outline: Invalid date parameters
    Given the address "/logbook"
    And the endpoint "/alerts?since=<since>&until=<until>"
    When a get request is performed
    Then the response code is 400
    Examples:
      | since               | until               |
      | 2018-01-01T00:00:00 | 2019-04-01T00:00:00 |
      | 2016-09-01T00:00:00 | 2015-09-01T00:00:00 |