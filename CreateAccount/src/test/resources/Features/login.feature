Feature: Creating accounts at mailchimp

  Scenario Outline: Create account that passes
    Given I have entered an "<email>" in the first text field
    And I also entered an "<username>" in the second text field
    And I also entered a "<password>" in the third text field
    When I press the sign Up button
    Then I "<verify>" the result

    Examples: 
      | email | username | password   | verify                                                                             |
      | email | working  | Hejsan123! | Check your email                                                                   |
      | email | char100  | Hejsan123! | Enter a value less than 100 characters long                                        |
      | email | taken    | Hejsan123! | Another user with this username already exists. Maybe it's your evil twin. Spooky. |
      | name2 | working  | Hejsan123! | Please enter a value                                                               |
      