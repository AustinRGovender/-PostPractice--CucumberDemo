package steps;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchSteps {

	WebDriver driver;

	@Before
	public void setup() {
		this.driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}

	@Given("^A user navigates to google website$")
	public void a_user_navigates_to_google_website() throws Throwable {
		driver.get("https://www.google.com");
	}

	@When("^The user enters text into the search bar$")
	public void the_user_enters_text_into_the_search_bar() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		driver.findElement(By.name("q")).click();
		driver.findElement(By.name("q")).sendKeys("cars");
	}

	@When("^Clicks the search button$")
	public void clicks_the_search_button() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("btnK")));
		driver.findElement(By.name("btnK")).click();
	}

	@Then("^Google returns cars\\.co\\.za as first site$")
	public void google_returns_cars_co_za_as_first_site() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#rso > div:nth-child(1) > div > div > div.r > a > h3")));

		WebElement carsLink = driver.findElement(By.cssSelector("#rso > div:nth-child(1) > div > div > div.r > a > h3"));
		assertEquals(true, carsLink.isDisplayed());
	}
	
	@After
	public void teardown()
	{
		driver.quit();
	}
}
