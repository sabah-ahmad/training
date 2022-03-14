package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.example.hooks.Hooks.*;

public class LoginSteps {

    private WebDriver driver;

    public LoginSteps(){
        this.driver = Hooks.getDriver();
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.edgewordstraining.co.uk/webdriver2/sdocs/auth.php");
    }
    @When("I use the username {string} and password {string}")
    public void i_use_the_username_and_password(String username, String password) {
        // Write code here that turns the phrase above into concrete actions

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.linkText("Submit")).click();
    }
    @Then("I am logged in")
    public void i_am_logged_in() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(2000);
        assertThat("We are not logged in",driver.findElement(By.linkText("Log Out")).isDisplayed(), is(true));
    }
}
