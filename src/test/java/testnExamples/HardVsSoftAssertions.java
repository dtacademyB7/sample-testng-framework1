package testnExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class HardVsSoftAssertions {

    // Hard vs Soft asseertions
    // Assert vs Verify


    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setup(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.manage().window().maximize();
    }

    @Test()
    @Parameters ("LINK")
    public  void login(String url){



        driver.get(url);
        driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester", Keys.TAB, "test", Keys.ENTER);
        Assert.assertTrue(driver.getCurrentUrl().equals("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/hhcsdvsdv"));
   // Hard assertions will stop the execution once they fail
        System.out.println("After 1 st assertion");
        driver.findElement(By.linkText("Order")).click();
        Assert.assertTrue(driver.getPageSource().contains("Order"));

    }


    @Test()
    public  void loginSoft() throws IOException {

        // Read/Write classes
        // Read/Write chars  - Scanner, PrintWriter, BufferedReader, BufferedWriter  -> .txt, .csv, .xml
        // Read/Write binary  - FileInputStream/FileOutputStream ->  binary files  .jpg, .docx, .xlsx

//        Properties properties = new Properties();
//        properties.load(new FileInputStream("testEnv.properties"));



        driver.get(PropertyReader.readProperty("url"));
        driver.findElement(By.name("ctl00$MainContent$username")).sendKeys(PropertyReader.readProperty("username"), Keys.TAB, PropertyReader.readProperty("pass"), Keys.ENTER);

        SoftAssert softAssert =  new SoftAssert();
        softAssert.assertTrue(driver.getCurrentUrl().equals("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/hhcsdvsdv"));

        // Soft assertions will not stop the execution even if they fail
        System.out.println("After 1 st assertion");
        driver.findElement(By.linkText("Order")).click();
        softAssert.assertTrue(driver.getPageSource().contains("Order"));

        softAssert.assertAll();

    }



    @AfterMethod
    public void tearDown(ITestResult testResult){
       if(testResult.getStatus() == ITestResult.FAILURE){
           //take a screenshot and attach to the report
       }

    }



}
