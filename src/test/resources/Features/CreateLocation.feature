Feature: Validate Create Location API with a valid payload

  @Regression
  Scenario Outline: Creating a new location using REST API
    Given Get the required payload
    When User calls the resource "<resource>" with http post request
    Then the API call status should get statusCode <statusCode>
    And the Status "<status>" in respond body is OK
    Examples:
      | resource                 | statusCode | status |
      | /maps/api/place/add/json | 200        | OK     |
      | /maps/api/place/add/json | 200        | OK     |
      | /maps/api/place/add/json | 200        | OK     |
      | /maps/api/place/add/json | 200        | OK     |
      | /maps/api/place/add/json | 200        | OK     |
      | /maps/api/place/add/json | 200        | OK     |

