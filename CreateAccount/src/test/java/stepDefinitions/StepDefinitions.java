package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;

public class StepDefinitions {
	
		private WebDriver driver;
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		String longName = "longnamelongnamelongnamelongnamelongnamelongnamelongnamelongnamelongnamelongnamelongnamelongnamelongname";
	
		@Before
		public void openBrowser() {
			System.setProperty("webdriver.chrome.driver", "C:\\Eclipse\\myDriver\\chromedriver.exe");
	        driver = new ChromeDriver();                 
	        driver.get("https://login.mailchimp.com/signup/");
	        driver.manage().window().maximize();
	        click(driver, By.cssSelector("button[id=onetrust-accept-btn-handler]"));    
		}
		
		private void click(WebDriver driver, By by) {
    		(new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(by));
                        driver.findElement(by).click();
        }
    				
    	private void sendKeys(WebDriver driver, By by, String keys) {
    		(new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(by));
    					driver.findElement(by).sendKeys(keys);
	}
		
		@Given("I have entered an {string} in the first text field")
		public void i_have_entered_an_in_the_first_text_field(String mail) {
			if(mail.equals("email")) {
				sendKeys(driver, By.name("email"), mail + randomInt + "@gmail.com");
			}else if(mail.equals("")) {
				
			}
		}

		@Given("I also entered an {string} in the second text field")
		public void i_also_entered_an_in_the_second_text_field(String usern) {
			if(usern.equals("taken")) {
				sendKeys(driver, By.name("username"), usern);
				}else if(usern.equals("char100")) {
					sendKeys(driver, By.name("username"), longName);
				}
				else if(usern.equals("working")) {
					sendKeys(driver, By.name("username"), usern + randomInt);
				}
			}
		
		
		@Given("I also entered a {string} in the third text field")
		public void i_also_entered_a_in_the_third_text_field(String passw) {
			sendKeys(driver, By.name("password"), passw);
					  
		}
				
			@When("I press the sign Up button")
			public void i_press_the_sign_up_button() {
				driver.findElement(By.id("create-account")).click();
			  }

	
		@Then("I {string} the result")
		public void i_the_result(String control) {
			if (control.equals("Check your email")) {
				  assertEquals(control, driver.findElement(By.cssSelector(".\\!margin-bottom--lv3")).getText());
				}else if(control.equals("Enter a value less than 100 characters long")) {
					assertEquals(control, driver.findElement(By.className("invalid-error")).getText());
				}else if(control.equals("Another user with this username already exists. Maybe it's your evil twin. Spooky.")) {
				    assertEquals(control, driver.findElement(By.className("invalid-error")).getText());
				}else if(control.equals("Please enter a value")) {
						assertEquals(control, driver.findElement(By.className("invalid-error")).getText());	
					}
				}

		@After
		public void teardown() {
			driver.quit();
		}
}