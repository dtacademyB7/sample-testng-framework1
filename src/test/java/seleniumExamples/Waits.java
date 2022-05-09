package seleniumExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//@Ignore
//@Test (enabled = false)
public class Waits {

    WebDriver driver;


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.manage().window().maximize();
    }

    @Test
    public  void implicitwWait() throws InterruptedException {
        // static wait - Thread.sleep()

        // dynamic waits - implicit and explicit


        // implicit
        // -set once, applied globally to all instances of findElement() method
        // - throws NoSuchElement exception



//
//        driver.get("https://www.google.com/");
//
//        // default polling frequency - 0.5 seconds
//
//        driver.findElement(By.name("w")).sendKeys("cdbscdgs");

        // explicit
        // -applies to a single element, has custom conditions
        // - TimeoutException: Expected condition failed is thrown if the timeout is finished


        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        driver.findElement(By.xpath("//button[.='Enable']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));


        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text']")));

//        WebElement w = driver.findElement(By.xpath("//input[@type='text']"));
//        explicitWait.until(ExpectedConditions.elementToBeClickable(w));

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("xcsvdhsvcsvsdh");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Thread.sleep(2000);


        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(NoSuchFrameException.class);





    }

//    @Ignore
//    @Test (enabled = false)
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
