Feature: Test invoice flow to Loyalty Application(LS) from Billing System(BS)

Scenario: Send Invoice from BS to LS and check updated point balance in LS
Given Set url for sending requests to LS from BS
When User send invoice to LS and check request status
Then check updated point balance in LS