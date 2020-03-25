package teamhomework21032020;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


    public class Computers {
   /*
Class Computers
url = https://demo.nopcommerce.com/"
Test 1 : Click on the Computers
         Navigate to Computers page successfully
         verify the Text Computers
Test 2 : Product added to Cart
         Click on Computer
         Click on first item - Desktops
         Clink on Build your own computer or Add to Cart
         (Goes to next page)
         Select radio button for  HDD * 400 GB [+$100.00]
         Then Add to Cart
         And verify message "The product has been added to your shopping cart"
 */



        WebDriver driver;

    @Before

    public void openBrowser() {
        String baseUrl = "https://demo.nopcommerce.com/";
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(baseUrl);


    }

    @Test

    public void verifyUserShouldNavigateToComputerPage() throws InterruptedException {

        WebElement computers = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]"));
        computers.click();

        WebElement comp = driver.findElement(By.xpath("//div[@class='page-title']//h1[contains(text(),'Computers')]"));
        Thread.sleep(2000);
        String expectedResult = "Computers";
        String actualResult = comp.getText();
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println("Expected message on Page : " + expectedResult);
        System.out.println("Actual message on Page : " + comp.getText());

    }

    @Test

    public void deskTopPage() throws InterruptedException {


        WebElement compu = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]"));
        compu.click();

        WebElement com = driver.findElement(By.xpath("//div[@class='page-title']//h1[contains(text(),'Computers')]"));
        com.click();

        WebElement desktop = driver.findElement(By.xpath("//h2[@class='title']//a[contains(text(),'Desktops')]"));
        desktop.click();
        Thread.sleep(3000);

        WebElement build = driver.findElement(By.xpath("//h2[@class='product-title']//a[contains(text(),'Build your own computer')]"));
        build.click();

        WebElement gb = driver.findElement(By.xpath("//input[@id='product_attribute_3_7']"));
        gb.click();

        WebElement add = driver.findElement(By.xpath("//input[@id='add-to-cart-button-1']"));
        add.click();

        WebElement confirm = driver.findElement(By.xpath("//p[@class='content']"));
        String expectedResult = "The product has been added to your shopping cart";
        String actualResult = confirm.getText();
        Assert.assertEquals(expectedResult, actualResult);

        System.out.println("Expected message on Page : " + expectedResult);
        System.out.println(" Actual message on Page : " +confirm.getText());





    }

    @After
    public void closingBrowser() {
        driver.close();
    }
}
