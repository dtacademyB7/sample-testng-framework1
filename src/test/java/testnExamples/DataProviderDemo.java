package testnExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class DataProviderDemo {



    @Test (dataProvider = "getData")
    public void google(String searchTerm){
        WebDriver driver;
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.manage().window().maximize();

        driver.get("https://www.google.com/");

        driver.findElement(By.name("q")).sendKeys(searchTerm, Keys.ENTER);

        Assert.assertTrue(driver.getTitle().contains(searchTerm));

        driver.quit();
    }


    @DataProvider
    public Object[][] getData(){

        return new Object[][]{
                {"chicken"},
                {"3624635"},
                {"()*&^&%$%"},
                {"http://www.slf4j.org/codes.html#StaticLoggerBinder"},


        };


    }
}
