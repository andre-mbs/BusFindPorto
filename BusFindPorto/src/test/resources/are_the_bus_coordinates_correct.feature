Feature: Are the bus coordinates correct?

    Scenario: Bus Location
        Given the bus number 00000000-0000-0000-0000-000000002518
        When the timestamp is 2018-10-08 00:00:00.001
        Then the bus longitude is -8.610583
        And the bus latitude is 41.14898