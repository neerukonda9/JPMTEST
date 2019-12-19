Feature: To validate authenticity of news article published in Guardian.
  As  the product owner I want to start with a simple confirmation by checking other sources to confirm a news article
  is valid

  Scenario: To verify fi the article is valid
    Given I navigate to NEWS_PAGE
    When I Get all articles from NEWS_PAGE
    And I verify news from GOOGLE
    Then I see recent articles published from other sources

  Scenario: search google resources when recent articles not found
    Given I've an article about nelson-mandela-obituary
    When I verify news from GOOGLE
    Then I see recent articles published from other sources
