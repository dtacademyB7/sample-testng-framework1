package seleniumExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TestSelenium {

    WebDriver driver;


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().fullscreen();
    }

    @Test
    public  void test1() throws InterruptedException {
        driver.get("https://www.google.com/");

//        driver.findElement(By.linkText("About")).click();

        driver.findElement(By.partialLinkText("Search works")).click();

        Thread.sleep(2000);
    }


    @Test
    public  void locate() throws InterruptedException {
        driver.get("https://www.google.com/");





//        StaleElementReferenceException - happens when the element that you are using became stale/old
        // This happens usually when you store element on the page and navigate away and come back and try to use that element again with the old reference
       // Example scenario: when you store links into a list and try to click on them one by one via navigating back to the main page
        // Solution -  grab the fresh reference for each navigation



        for (int i = 0; i < driver.findElements(By.tagName("a")).size() ; i++) {
            Thread.sleep(1000);
            driver.findElements(By.tagName("a")).get(i).click();
            driver.navigate().back();
        }


//        Thread.sleep(2000);
    }


    @Test
    public  void test3() throws InterruptedException {
        driver.get("https://www.google.com/");

//        driver.findElement(By.linkText("About")).click();

        WebElement link = driver.findElement(By.linkText("About"));

        System.out.println(link.isEnabled());
        System.out.println(link.isDisplayed());
        System.out.println(link.isSelected()); // checkboxes, radio button


        String text = driver.findElement(By.xpath("//div[@class='KxwPGc AghGtd']")).getText();

        System.out.println(text);


        // getAttribute()
        System.out.println(driver.findElement(By.linkText("Store")).getAttribute("href"));

        driver.findElement(By.name("q")).sendKeys("Blalbla");

        System.out.println(driver.findElement(By.name("q")).getAttribute("value"));

        Thread.sleep(2000);
    }


    @Test
    public  void test5() throws InterruptedException {
        driver.get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");
        System.out.println(driver.findElement(By.tagName("h1")).getCssValue("color"));
        System.out.println(driver.findElement(By.tagName("h1")).getCssValue("font-family"));
    }

    @Test
    public  void test6() throws InterruptedException {
        driver.get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");

        driver.findElement(By.id("loginUsername")).sendKeys(Keys.chord(Keys.SHIFT, "User"), Keys.TAB, "password", Keys.ENTER );



    }

    @Test
    public  void test7() throws InterruptedException {
        driver.get("https://www.google.com/");
     //   /html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input - full xpath

     //   //input[@name='q'] - relative xpath


     //     input[name='q']  -cssSelector

      // Differences between xpath and css
        // -css is faster
        // -xpath can traverse forwards and backwards in html when locating, css can only forwards
        // css cannot locate an element based on its text
        // -xpath has more features and methods that lets you locate elements efficiently
        // -xpath doesn't always work with all browsers, css is native so it works the same among all browsers



     // Use element attributes

        //input[@class='someClass'][@href='someHref'] //

        //a[contains( @class, 'bla'   ) ]  //find a element that has an attribute that contains bla

     // Text

        //a[.='About']   // exact match

        //a[contains( text(), 'Search works'   ) ]  //  partial text


    }






        @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
