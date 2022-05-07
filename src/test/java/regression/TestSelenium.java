package regression;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TestSelenium {

    WebDriver driver;


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();
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



    @Test
    public  void dynamic() throws InterruptedException {


        // elements with dynamic attributes can be located using these methods

        //style[starts-with(@data-iml, '1651689')]
        //style[contains(@data-iml, '5168')]
        //style[ends-with(@data-iml, '1689')]


        // if the attribute a value is entirely dynamic use other strategies
        // use parent,child or sibling
        // use full xpath - /html/body/script[1]


    }

    @Test
    public void useFaker() throws InterruptedException {

        driver.get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");

        driver.findElement(By.id("hideLogin")).click();

        Faker faker =  new Faker();

//        System.out.println(faker.name().username());

        driver.findElement(By.id("username")).sendKeys(faker.name().username());
        Thread.sleep(2000);
    }




    //  unique parent -> non-unique child

    //div[@class='wrapper w-container']//div[@data-ix='fade-up-2']

    // unique child -> non-unique parent

    //img[@src='https://uploads-ssl.webflow.com/5f3f0c2b700858585d26d1d4/5f3f0c2c55e93a1caf05d2e7_marketing-icon.png']/parent::div


    // unique sibling  -> following/preceding sibling

    //div[@data-w-id='6ece7d70-83a5-6ab9-85ca-d3b52d1df6a5']/following-sibling::div
    //div[@data-w-id='c339b283-03ea-c60c-bb22-0601a49dde47']/preceding-sibling::div[1]


    @Test
    public void dropDownBoxes() throws InterruptedException {

        // dropdown boxes are handled using Select class

        driver.get("https://www.cars.com/");

        WebElement selectBox = driver.findElement(By.id("make-model-search-stocktype"));

        Select select =  new Select(selectBox);


//        select.selectByVisibleText("Used cars");
//        select.selectByIndex(4);  // 0 based
        //        select.selectByValue("used");

        WebElement firstSelectedOption = select.getFirstSelectedOption();
        System.out.println(firstSelectedOption.getText());

        List<WebElement> options = select.getOptions();


        for (WebElement option : options) {
            System.out.println(option.getText());
        }
        Thread.sleep(2000);


        //


    }


    @Test
    public void radioButtonAndCheckboxes() throws InterruptedException {


        // When do you get ElementNotInteractableException: element not interactable?
//
//        driver.get("https://designsystem.digital.gov/components/radio-buttons/");
//
//        List<WebElement> elements = driver.findElements(By.xpath("//fieldset[@class='usa-fieldset'][1]//input[@type='radio']"));
//
//        WebElement element = elements.get(new Random().nextInt(elements.size()));
//        if(element.isEnabled()){
//            elements.get( new Random().nextInt(elements.size())).click();
//        }


        driver.get("https://the-internet.herokuapp.com/checkboxes");

        List<WebElement> elements1 = driver.findElements(By.xpath("//form//input[@type='checkbox']"));

        for (WebElement webElement : elements1) {
            if(!webElement.isSelected())
                webElement.click();
        }

        Thread.sleep(2000);


    }


    @Test
    public void webElements(){



        //table[@class='ProductsTable']//tr[1]//th  -  get the header row


        //table[@class='ProductsTable']//tr//td[1] - get the first column


        driver.get("https://www.livecoinwatch.com/");

        List<WebElement> elements = driver.findElements(By.xpath("//table[@class='lcw-table layout-fixed']//tr//td[8]//span"));


       List<Double> nums = new ArrayList<>();
        for (WebElement element : elements) {

            Double each = Double.parseDouble(element.getText().replace("%", ""));

            nums.add(each);
        }


        Collections.sort(nums);

        System.out.println("The largest change is: "  + nums.get(nums.size()-1 ) ) ;

        // row count
        int row = elements.size();
        // column no
        int columnCount = driver.findElements(By.xpath("//table[@class='lcw-table layout-fixed']//tr[@class='filter-row table-row table-heading filter-heading']//th")).size();

        for (int i = 1; i <= row ; i++) {



            for (int j = 1; j <= columnCount; j++) {

                String xpath = "//table[@class='lcw-table layout-fixed']//tr["+i+"]//td["+j+"]";

                System.out.print(driver.findElement(By.xpath(xpath)).getText() + "\t");

                
            }

            System.out.println();
            
        }


    }



    @Test
    public void alert() throws InterruptedException {

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
//        driver.findElement(By.xpath("//button[.='Click for JS Alert']")).click();
//
//        Alert alert = driver.switchTo().alert();
//        alert.accept();
//        Thread.sleep(1000);


//        driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();
//
//        Alert alert = driver.switchTo().alert();
//        alert.dismiss();
//        Thread.sleep(1000);


        driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("C vdgschgds d sd sgh");
        alert.accept();


        //NoAlertPresentException: no such alert - when the alert is not there anymore
        System.out.println(alert.getText());
        Thread.sleep(1000);

        //If you don't handle the JS alert
        //UnhandledAlertException: unexpected alert open: {Alert text : I am a JS Alert}
        driver.findElement(By.linkText("Elemental Selenium")).click();
    }



    @Test
    public void iframes(){

        driver.get("https://www.duotech.io/enroll-sqae2");

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id, '1651')]")));


        driver.findElement(By.xpath("//input[@placeholder='First']")).sendKeys("Akmal");


        driver.switchTo().defaultContent(); //main window
        driver.switchTo().parentFrame(); //switches to the immediate parent frame



    }


@Test
    public void switchWindows(){
        driver.get("https://www.toyota.com/");
        driver.findElement(By.linkText("DO NOT SELL MY PERSONAL INFORMATION")).click();

        // use window handle
    String current = driver.getWindowHandle();//current window's handle
    Set<String> windowHandles = driver.getWindowHandles();

//    for (String windowHandle : windowHandles) {
//        if(!windowHandle.equals(current)){
//            driver.switchTo().window(windowHandle);
//            break;
//        }
//    }

    // use window title - multiple windows

    for (String windowHandle : windowHandles) {
        driver.switchTo().window(windowHandle);
        if(driver.getTitle().equals("Toyota - Lexus Privacy Hub")){
            break;
        }
    }



    System.out.println(driver.getTitle());
    }





    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
