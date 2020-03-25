package teamhomework21032020;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;
/*
url = "https://demo.nopcommerce.com/"
Class Electronics
Test 1  mouseHoverToElectronics
          mouse hover to Electronics
          mouse hover to Camera & photo
          verify text Camera & Photo
Test 2 : Position High to Low
         verify descending order
*/
public class Electronics {

    WebDriver driver;
    private JavascriptExecutor jse;

    @Before
    public void openBrowser() {

        String baseUrl = "https://demo.nopcommerce.com/";
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        jse = (JavascriptExecutor) driver;
    }

    @Test
    public void mouseOverElectronics() throws InterruptedException {
        jse.executeScript("window.scrollBy(0, 800)");
        WebElement electronis = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronis).perform();
        Thread.sleep(3000);

        WebElement cameraPhoto = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Camera & photo')]"));
        actions.moveToElement(cameraPhoto).click().perform();

        WebElement cameraMessage = driver.findElement(By.xpath("//h1[contains(text(),'Camera & photo')]"));
        String expectedResult = "Camera & photo";
        String actualResult = cameraMessage.getText();
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println("Expected message on page : " +expectedResult);
        System.out.println("Actual message on page : " +actualResult);
    }

    @Test
    public void assertPositionLowToHigh() {
        jse.executeScript("window.scrollBy(0, 800)");
        WebElement electronis = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronis).perform();
        WebElement cameraPhoto = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Camera & photo')]"));
        actions.moveToElement(cameraPhoto).click().perform();

        WebElement sortByPosition = driver.findElement(By.xpath("//select[@id='products-orderby']"));
        Select select = new Select(sortByPosition);
        select.selectByVisibleText("Price: Low to High");

        List<WebElement> elements = driver.findElements(By.className("prices"));
        List<String> webSortedPrices = elements.stream().map(WebElement::getText).collect(toList());
        List<String> mySortedPrices = new ArrayList<>();
        for (WebElement element : elements) {
            String text = element.getText();
            mySortedPrices.add(text);
        }

        Assert.assertEquals(webSortedPrices, mySortedPrices);
        System.out.println(mySortedPrices);
        System.out.println(webSortedPrices);

    }

    @After
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
