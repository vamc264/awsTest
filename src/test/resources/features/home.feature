Feature: BitRise TestApp
  As a Bit rise user
  I verify the app

  Background:
    Given I verify the app

  @bitRise
  Scenario: Verify and tap on button one
    When I tap on button one
    Then I verify the button one pressed text

#  @test
#  Scenario: Verify and tap on button two
#    When I tap on button two
#    Then I verify the button two pressed text