package com.test;

import com.test.enums.PageEnums;
import com.test.pages.NewsPage;
import com.test.pages.searchsource.google.GoogleHomePage;
import com.test.pages.searchsource.google.SearchResultsPage;
import com.test.sessionData.SessionData;
import com.test.util.NavigationHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsValidationStepDef {

    @Autowired
    private NewsPage newsPage;
    @Autowired
    private SessionData sessionData;
    @Autowired
    private GoogleHomePage googleHomePage;
    @Autowired
    private NavigationHelper navigationHelper;
    @Autowired
    private SearchResultsPage searchResultsPage;

    @Value("${url}")
    private String Url;

    @Given("^I navigate to (.*)$")
    public void navigateToNewsPage(PageEnums pageEnums) {
        navigationHelper.navigateToPage(Url + pageEnums.getPageUlr());
    }

    @When("I Get all articles from NEWS_PAGE")
    public void getAllArticlesFromNEWS_PAGE() {
        sessionData.setData("articles", newsPage.getAllArticles());
    }

    @When("^I verify news from (.*)$")
    public void iVerifyTheNewsFrom(PageEnums pageEnums) {
        navigationHelper.navigateToPage(pageEnums.getPageUlr());
        List<String> newsArticles = sessionData.getData("articles");
        googleHomePage.searchForAnArticleTitle(newsArticles.get(0));
    }

    @Then("I see recent articles published from other sources")
    public void iSeeRecentArticlesBeenPublished() {
        List<String> guardianNewsArticles = sessionData.getData("articles");

        List<String> searchResultsUpdatedTime = searchResultsPage.getSearchResultsUpdatedTime(Url);
        Assert.assertTrue(" \n\nArticle trying to publish \n \n" +
                        guardianNewsArticles.get(0) +
                        " \n \nnot found from the other sources\n\n",
                searchResultsUpdatedTime.size() > 0);
    }


    @Given("I've an article about (.*)")
    public void iVeAnArticleAbout(String articleTitle) {
        sessionData.setData("articles", new ArrayList<>(Arrays.asList(articleTitle)));
    }
}
