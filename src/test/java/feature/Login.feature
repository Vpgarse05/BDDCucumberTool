@Regression
Feature: Testing Login Functionality of CX Choice Web Application.

  Background:Perform Login before every scenario.
    Given User should be on CX Choice login page.
    Given User enter user id "AlokDixit" on email username text field.
    And User enter password "Hello12345!" on password text field.
    And User click on the login button.

  @Vendor @VTC_01
  Scenario: Verify thr vendor should be able to login with vendor credential.
    Then Verify user should be able to log in to the CX Choice.

  #Scenario Outline: Verify that Vendor can login or can go in vendor view from staff view and land on to Vendor dashboard page

  Scenario Outline: Tool vise execution
    Given Launch the "CHROME" browser.
    Then Navigate to the "URL" web application.
    Then Perform sendKeys "ENTER" value "textField" to the "<value>" field.
    Then Perform click "CLICK" on "clickElement" the element.
    Then Perform jsClick "JSCLICK" on "clickElement" the element.
    Then Perform action click "ACTCLICK" on "clickElement" the element.
    Then User select the "selectElement" by index "<indexNumber>" from "SELECT" select type dropdown.
    Then User select the "selectElement" by visible value "<visibleValue>" from "SELECT" select type dropdown.
    Then Perform mouse hover "HOVER" on "overElement" element.
    Then Add "STATIC" Wait for the "<time>" seconds.
    Then Perform get text "GETTEXT" for "textElement" element and store "" in variable.
    Then User Switch to new window "NEWWINDOW".
    Then User Switch to home window "HOMEWINDOW".
    Then Upload "UPLOAD" file "<file>" to the location "uploadLocator" field element.
    Then User "DRAGDROP" drag "source" element and drop to "target" target element.
    Then Close the open browser.
    Examples:
      | indexNumber | value | visibleValue | time | file |  |
