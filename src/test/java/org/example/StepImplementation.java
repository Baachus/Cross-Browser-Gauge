package org.example;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation {
    @Step("Go to Gauge Get Started Page")
    public void gotoGetStartedPage() throws InterruptedException {
        WebElement getStartedButton = Driver.webDriver.findElement(By.xpath("//a[@href='https://docs.gauge.org/getting_started/installing-gauge.html']"));
        getStartedButton.click();

        Gauge.writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Go to Gauge Documentation Page")
    public void gotoDocumentationPage() throws InterruptedException {
        WebElement documentationLink = Driver.webDriver.findElement(By.cssSelector("ul[class='main-nav']>li>a[href='https://docs.gauge.org']"));
        documentationLink.click();

        Gauge.writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Go to Gauge Plugins Page")
    public void gotoPluginsPage() throws InterruptedException {
        WebElement pluginsLink = Driver.webDriver.findElement(By.cssSelector("li[class='link_plugins']>a"));
        pluginsLink.click();

        Gauge.writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Go to Gauge Blog Page")
    public void gotoBlogPage() throws InterruptedException {
        WebElement blogLink = Driver.webDriver.findElement(By.cssSelector("li[class='link_blog']>a"));
        blogLink.click();

        Gauge.writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Go to Gauge Contribution page")
    public void gotoContributionPage() {
        WebElement contributionLink = Driver.webDriver.findElement(By.cssSelector("a[class='icon_contribute']"));
        contributionLink.click();

        Gauge.writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Ensure installation instructions are available")
    public void ensureInstallationInstructionsAreAvailable() throws InterruptedException {
        WebElement instructions = Driver.webDriver.findElement(By.xpath("//a[@href='/writing-specifications.html']"));
        assertThat(instructions).isNotNull();
    }

    @Step("Open the Gauge homepage")
    public void navigateToHomepage() {
        String app_url = System.getenv("APP_URL");
        Driver.webDriver.get(app_url + "/");
        assertThat(Driver.webDriver.getTitle()).contains("Gauge");
    }

    @Step("Ensure documentation title exists")
    public void validationOfDocumentation() {
        WebElement documentationLink = Driver.webDriver.findElement(By.cssSelector("h1[class='banner-heading']"));
        assertThat(documentationLink.getText().contains("Gauge Documentation"));
    }

    @Step("Ensure plugins title exists")
    public void validationOfPlugins() {
        WebElement documentationLink = Driver.webDriver.findElement(By.cssSelector("header[class='page-header']>h1"));
        assertThat(documentationLink.getText().contains("Plugins"));
    }

    @Step("Ensure installing Gauge section exists")
    public void validationOfInstallation() {
        WebElement documentationLink = Driver.webDriver.findElement(By.cssSelector("h1>span[class='heading']"));
        assertThat(documentationLink.getText().contains("Installing Gauge"));
    }

    @Step("Verify the following sections exist <table>")
    public void validateContributionSections(Table table) throws InterruptedException {
        table.getTableRows().stream().forEach(tableRow -> {
            if (!tableRow.getCell("title").toLowerCase().equals("title")) {
                assertThat(Driver.webDriver.getPageSource().contains(tableRow.getCell("title")));
            }
        });
    }

    @Step("Verify the following points are displayed <table:resources/developers_section_titles.csv>")
    public void validateContributionSectionsCSV(Table table) throws InterruptedException {
        table.getTableRows().stream().forEach(tableRow -> {
            if (!tableRow.getCell("title").toLowerCase().equals("title")) {
                assertThat(Driver.webDriver.getPageSource().contains(tableRow.getCell("title")));
            }
        });

    }

    @Step("Ensure description is displayed correctly matching <file:introduction.txt>")
    public void Description(String content) throws InterruptedException {
        String temp = Driver.webDriver.findElement(By.cssSelector("p[class='banner-description'")).getText();
        assertThat(Driver.webDriver.findElement(By.cssSelector("p[class='banner-description'")).getText().equals(content));
    }
}
