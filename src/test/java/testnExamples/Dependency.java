package testnExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Dependency {


    WebDriver driver;

    @BeforeClass (alwaysRun = true)
    public void setup(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.manage().window().maximize();
    }

    @Test  (groups = {"smoke"})
    public  void login(){

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
       driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester", Keys.TAB, "test", Keys.ENTER);
       Assert.assertTrue(driver.getCurrentUrl().equals("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/"));

    }


    @Test (dependsOnMethods = "login")
    public  void clickOrder(){


        driver.findElement(By.linkText("Order")).click();
        Assert.assertTrue(driver.getPageSource().contains("Order"));

    }




}
