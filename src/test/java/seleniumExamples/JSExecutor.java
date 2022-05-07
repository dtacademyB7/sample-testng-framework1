package seleniumExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class JSExecutor {


    WebDriver driver;


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();

//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        ChromeOptions chromeOptions =  new ChromeOptions();
//        chromeOptions.addArguments("--headless");


        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.manage().window().maximize();
    }


    @Test
    public  void method(){

        driver.get("https://www.google.com/");
       JavascriptExecutor js = (JavascriptExecutor)driver;
//       js.executeScript( "alert('I am an alert box!')");
        js.executeScript("arguments[0].click();", driver.findElement(By.linkText("About")));

        js.executeScript("window.scrollBy(0,1000)");


    }

    @Test
    public  void file(){

        driver.get("https://www.filemail.com/share/upload-file");

        driver.findElement(By.xpath("//span[.='Add Files']//following-sibling::input")).sendKeys("/Users/duotech/Downloads/Batch5.zip");


    }

    @Test
    public  void takesScreenshot() throws IOException {



        driver.get("https://www.filemail.com/share/upload-file");


        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(screenshotAs, new File("screenshot.png"));

    }


    @Test
    public  void actionsClass(){

        // Actions class - Advanced user actions

        Actions actions =  new Actions(driver);

        driver.get("https://www.google.com/");
        WebElement input = driver.findElement(By.name("q"));

        actions.keyDown(input,Keys.SHIFT).sendKeys(input, "cdsc").sendKeys(input, "cdbssd").keyUp(input,Keys.SHIFT).build().perform();

        actions.sendKeys(input, "dscds").build().perform();

    }

    @Test
    public  void actionsClass2(){

        // Actions class - Advanced user actions

        Actions actions =  new Actions(driver);

        driver.get("https://www.amazon.com/");
        WebElement link = driver.findElement(By.id("nav-link-accountList"));

        // How do you hover over an element with a mouse in Selenium?

//        actions.moveToElement(link).build().perform();


     //   ElementNotInteractableException - when element is on the html but not interactable (hidden, invisible, disabled)

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@href='/gp/subs/primeclub/account/homepage.html?ref_=nav_AccountFlyout_prime']")) );

//        driver.findElement(By.xpath("//a[@href='/gp/subs/primeclub/account/homepage.html?ref_=nav_AccountFlyout_prime']")).click();

    }



}
