package seleniumExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
}
