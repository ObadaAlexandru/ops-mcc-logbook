@component-test
Feature: Get individual logs and search by property

  Scenario: Get log by id
    Given the endpoint "/logbook/logs/5"
    When a get request is performed
    Then the response code is 200
    And the body contains the following fields:
      | fieldName    | value               |
      | logId        | 5                   |
      | message      | test message6       |
      | severity     | WARNING             |
      | subsystem    | COM                 |
      | createdOn    | 2015-05-05T16:30:25 |
      | downloadedOn | 2017-03-05T16:30:25 |

  Scenario Outline: Get logs by property
    Given the endpoint "/logbook/logs?<property>=<value>"
    When a get request is performed
    Then the response code is 200
    And the response contains <items> items
    Examples:
      | property    | value   | items |
      | severity    | WARNING | 2     |
      | subsystemId | COM     | 5     |


  Scenario Outline: Get logs by date
    Given the endpoint "/logbook/logs?since=<since>&until=<until>"
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
      Given the endpoint "/logbook/logs?since=<since>&until=<until>"
      When a get request is performed
      Then the response code is 400
    Examples:
    | since               | until               |
    | 2018-01-01T00:00:00 | 2019-04-01T00:00:00 |
    | 2016-09-01T00:00:00 | 2015-09-01T00:00:00 |