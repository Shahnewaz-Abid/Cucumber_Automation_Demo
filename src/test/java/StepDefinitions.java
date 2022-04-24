import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.Login;

import java.time.Duration;

public class StepDefinitions {
    public WebDriver driver;
    Login login;

    @Given("user visits to the HRM portal")
    public void user_visits_to_the_hrm_portal() {
        // Write code here that turns the phrase above into concrete actions
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops = new FirefoxOptions();
        ops.addArguments("--headed");
        driver = new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com/");

    }
    @When("user inputs invalid {string} and {string}")
    public void user_inputs_invalid_username_and_password(String username, String password) {
        // Write code here that turns the phrase above into concrete actions
        login = new Login(driver);
        login.doLogin(username, password);
    }
    @Then("user can not log in")
    public void user_can_not_log_in() {
        // Write code here that turns the phrase above into concrete actions
        login = new Login(driver);
        String text = login.getErrorMessage();
        Assert.assertEquals(text,"Invalid credentials");
        driver.close();
    }
}
