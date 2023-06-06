import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class SeleniumPractice {

    static WebDriver driver = null;

    //launch driver
    public static void launchDriver() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-notifications");
        options.addArguments("disable-infobars");
        options.addArguments("disable-geolocation");
    }

    //delete Cookies , maximize , dynamic waits
    public static void requisites() {
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    // Static date Picker and dynamic table
    @Test(enabled = true, priority = 1)
    public static void staticDatePickerAndDynamicTable() throws InterruptedException {
        launchDriver();
        driver.get("https://www.redbus.in/");
        WebElement clickCalendar = driver.findElement(By.cssSelector("span[class = 'fl icon-calendar_icon-new icon-onward-calendar icon']"));
        clickCalendar.click();
        WebElement date = driver.findElement(By.xpath("//table[@class = 'rb-monthTable first last']/tbody/tr[7]/td[4]"));
        date.click();
        System.out.println("Static Table Test is success");
        //dynamicTable::
        clickCalendar.click();
        WebElement arrowButton = driver.findElement(By.xpath("//table[@class = 'rb-monthTable first last']/tbody/tr[1]/td[3]/button"));
        arrowButton.click();
        List<WebElement> weekList = driver.findElements(By.xpath("//table[@class = 'rb-monthTable first last']/tbody/tr[4]/td"));
        int length = weekList.size();
        System.out.println(length);
        for (int i = 0; i < length; i++) {
            String dateText = weekList.get(i).getText();
            System.out.println(dateText);
            if (dateText.equalsIgnoreCase("8")) {
                weekList.get(i).click();
            }
            System.out.println("Dynamic Table is success");
        }
    }

    //Dynamic Table 2::
    public static void dynamicTableTest2() throws InterruptedException {
        launchDriver();
        driver.get("https://demo.guru99.com/test/web-table-element.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //To find third row of table
        WebElement baseTable = driver.findElement(By.tagName("table"));
        WebElement tableRow = baseTable.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]"));
        String rowtext = tableRow.getText();
        System.out.println("Third row of table : " + rowtext);
        //to get 3rd row's 2nd column data
        WebElement cellIneed = tableRow.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]/td[2]"));
        String valueIneed = cellIneed.getText();
        System.out.println("Cell value is : " + valueIneed);
        driver.close();
    }

    //JavaScript Scroll Down
    public static void javascriptExecutorScrollDown(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", element);

    }

    //footerLinkTest()
    public static void footerLinkTest() throws InterruptedException {
        int count = 0;
        launchDriver();
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement footerdriver = driver.findElement(By.xpath("//*[@class= 'navFooterLinkCol navAccessibility'][1]"));
        javascriptExecutorScrollDown(footerdriver);
        List<WebElement> links = footerdriver.findElements(By.tagName("a"));
        System.out.println("Number of Links Present is" + links.size());
        for (int i = 0; i < links.size(); i++) {
            links.get(i).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
            Thread.sleep(1000);

        }

        //broken links

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String title = driver.getTitle();
        driver.manage().timeouts().implicitlyWait(30,
                TimeUnit.SECONDS);
        String url = driver.getCurrentUrl();
        System.out.println("the URL is" + url + "and the title is" + title);
        if (title.contains("400")) {
            System.out.println("broken link Found");
            count++;
        }
        System.out.println("Count of broken Links is " + count);
    }

    //Window handle
    public static void windowHandlesTest() throws InterruptedException {
        launchDriver();
        driver.get("http://www.naukri.com/");
        // It will return the parent window name as a String
        String parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        // Now iterate using Iterator
        Iterator<String> I1 = s.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
                driver.close();
            }
        }
        //switch to the parent window
        driver.switchTo().window(parent);
    }

    //handling javascript alert
    @Test(enabled = false)
    public void javascriptAlert() throws InterruptedException {
        launchDriver();
        try {
            driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id='login1']")).sendKeys("abc");
        driver.findElement(By.xpath("//*[@title= 'Sign in']")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
        driver.findElement(By.xpath("//*[@id='login1']")).sendKeys("abc");
        driver.findElement(By.xpath("//*[@title= 'Sign in']")).click();
        alert.dismiss();
        System.out.println("Test 3 passed");
        // File Upload
        WebElement signIn = driver.findElement(By.xpath("//*[@title= 'Signin']"));
        signIn.sendKeys("/users/dk/extent.html");
    }

    //handling frames
    @Test(enabled = false)
    public void frameHandling() throws InterruptedException {
        launchDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        requisites();
        List<WebElement> iframeElements = driver.findElements(By.id("iframe"));
        System.out.println("Total number of iframes are " + iframeElements.size());
        driver.switchTo().frame("iframe");
        driver.findElement((By.xpath("//a[contains(text(),'Courses')]"))).click();
        driver.switchTo().defaultContent();
        System.out.println("iframe Passed");

    }

    //mouseMovements()
    @Test(enabled = false)
    public void mouseMovements() throws InterruptedException, AWTException {
        launchDriver();
        driver.get("https://www.spicejet.com/");
        requisites();
        Thread.sleep(3000);
        Robot robot = new Robot();
        robot.delay(5000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_ENTER);
        System.out.println("Robot class worked");
        //Actions Class
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'Add-ons')]"))).build().perform();
        Thread.sleep(3000);
        System.out.println("mouse Movement passed");
        driver.findElement(By.xpath("//*[contains(text(),'SpiceMAX')]")).click();
        System.out.println("SpiceMax clicked");
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(0);

        // action.clickAndHold(driver.findElement(By.xpath("//*[contains(text(),'Drag me to my target')]")))
        // .moveToElement(driver.findElement(By.xpath("//*[@id ='droppable']")).release() .build() .perform());

    }
    //dynamic xpath
    public void dynamicXpathTest()
    {
      driver.findElement(By.xpath("//*[contains(text(),'SpiceMAX')]"));
      driver.findElement(By.xpath("//*[starts-with(@id,'SpiceMAX')]"));
      driver.findElement(By.xpath("//*[ends-with(@id,'SpiceMAX')]"));
      driver.get("https://www.ebay.com/");
      List<WebElement> linkslist =driver.findElements(By.tagName("a") );
      System.out.println(linkslist.size());
      for(int i=0; i<linkslist.size();i++) {
          String linkTest = linkslist.get(i).getText(); System.out.println(linkTest);
      }
    // How may ways you can refresh a page
        /*
         * driver.get("url1");
         * driver.navigate().to("url2");
         * driver.navigate().back();
         * driver.navigate().forward();
         * driver.navigate().refresh();
         */
}
    //waits in selenium

    //headless browser
  /*  @Test(enabled = false)
    public void testHeadlessBrowser() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1400,800");
        options.addArguments("headless");
     // options.addArguments("--headless", "--window-size=1920,1200");
        driver = new ChromeDriver(options);
        driver.get("https://www.spicejet.com/");
        System.out.println("Headless browser Test Passed");
   */

    // 9.headless browser 2
 /*   @Test(enabled = false)
    public void testHeadlessBrowserHTMLUnitDriver() {
// System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
// ChromeOptions options = new ChromeOptions();
// options.addArguments("window-size=1400,800");
// options.addArguments("headless");
// options.addArguments("--headless", "--window-size=1920,1200");
        driver = new HtmlUnitDriver();
        driver.get("https://www.spicejet.com/");
        System.out.println("HTML Headless browser Test Passed ");
*/
    // 10.highlight element ==> JS is a class and we have to cast our driver to it.
   // executeScript is used to code in JS
   /* public static void highlightElementTest(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String bgcolor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 50; i++) {
            changeColor("rgb(0,200,0)", element);
            changeColor(bgcolor, element);
        }*/
    //Assert Tests::
    public void asserttest() throws InterruptedException {
        launchDriver();
        driver.navigate().to("https://www.browserstack.com/");
        requisites();
        String ActualTitle = driver.getTitle();
        String verifyAssertNull=null;
    String ExpectedTitle = "Most Reliable App & Cross Browser Testing Platform | BrowserStack";
    Boolean verifyTitleIsPresent=driver.getTitle().equalsIgnoreCase("Most Reliable App & Cross Browser Testing Platform | BrowserStack");
    Boolean verifyTitleIsChanged=driver.getTitle().equalsIgnoreCase("Testing Platform | BrowserStack");
    assertEquals(ExpectedTitle, ActualTitle);
    assertNotEquals(ExpectedTitle, "browserstack");
    assertTrue(verifyTitleIsPresent);
    assertFalse(verifyTitleIsChanged);
    assertNotNull(verifyTitleIsPresent);
    assertNull(verifyAssertNull);
}

    }









    //DataProvider ::
    //ExceptionTest::
    //MakeMytriptest::
    //SoftAsert and hard Assert and verify
    //TestNG Parameter Test
    //Retry Failed Test


    //Launch Chrome n other browsers
    // dynamic wait





    //browser pop ups
    //Windows Handler

    //highlight element
    //changeColor
    //testHighLightElement()
    //javascript executor
    //generate Alert
    //click Element By Javascript Executor
    // refresh the browser by JS
    // get Title by JS
    //getPageInnerText
    //scrollPageDown
    //scrollIntoView
    //isdisplyed.isenabled.isSelect
    //Dynamic web table
    //handle calender
    //handle calendar by javascript executor
    //handle bootstrap dropdown
    //state element exception
    //Select Dropdown
    //.take screenshots and add to extent report
    //locator strategy in webdriver
    //chat bot using selenium
    //use hashmap in selenium
    //loginWithAdminUsertest()
    //loginWithCustomerUsertest()
    //click on back and forward buttons
    //download a file in selenium
    //downloadFileTestActual()
    //actions class
    //properties file
    //use singleton class in selenium
    //automate OTP number in selenium
    //read and write to and from excel
    //Handle SVG elements in selenium
    //multithreading concept in selenium
    //handle auth popup in selenium
    //broken links ==> Check vdo 29
    // 29.Dynamic xpath coding
    //  CSS selector
    // shadow elements
    // jira integration with selenium
    // selenium integration with saucelabs
    // selenium Grid
    // automate complex graphs
    // tricky iframe shadowroot
    // write webtables values to excel
    // Real time use case of hashmap
    //TestngBasics
    //Hidden Elements
    //Calender function ::
    //Window Handler




