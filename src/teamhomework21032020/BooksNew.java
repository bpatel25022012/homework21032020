package teamhomework21032020;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class BooksNew {

    /*
Class BooksNew
url = https://demo.nopcommerce.com/"

Test 1 : Click on the books Page
         Navigate to books page successfully
         verify the Text Books
Test 2 : Books Page (sort by)
         Select position (A-Z)
         arrange the books list in ascending order and verify
Test 3 : Add book Fahrenheit 451 by Ray Bradbury to the wish list
         And verify the message "The product has been added to your wishlist"
 */

    String baseUrl = "https://demo.nopcommerce.com/";
    WebDriver driver;


    @Before

    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test

    public void verifyUserShouldNavigateToBooksPage(){
        WebElement bookslink = driver.findElement(By.linkText("Books"));
        bookslink.click();
        WebElement welcomeText = driver.findElement(By.xpath("//div[@class=\"page-title\"]//h1"));

        String expectedText = "Books";
        String actualText = welcomeText.getText();
        Assert.assertEquals(expectedText,actualText);
        System.out.println("Expected message on Page : " + expectedText);
        System.out.println("Actual message on Page : " + welcomeText.getText());
    }

    @Test
    public void booksPage() throws InterruptedException {

        WebElement books = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Books')]"));
        books.click();

        // This logic will get text from web element and sorting it and compare it.
        WebElement sortBy = driver.findElement(By.xpath("//option[contains(text(),'Name: A to Z')]"));
        sortBy.click();

        ArrayList<String> obtainedList = new ArrayList<>();  // creating the array list for obtain the elements from webpage

        List<WebElement> elementList =driver.findElements(By.tagName("h2"));// finding the elements by tag

        for (WebElement Links :elementList){
            obtainedList.add(Links.getText());
        }
        System.out.println("Obtained Product List :" + obtainedList);

        ArrayList<String> sortedList = new ArrayList<>(); // creation of sorting list array

        sortedList.addAll(obtainedList); // adding all list form obtainedblist

        Collections.sort(sortedList); // collections method to sort list

        Assert.assertTrue(obtainedList.equals(sortedList)); // assert of both listy

        System.out.println("Sorted Product List :" + sortedList);



    }
    @Test
    public void wishList() throws InterruptedException {

        WebElement books = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Books')]"));
        books.click();

        WebElement sortBy = driver.findElement(By.xpath("//option[contains(text(),'Name: A to Z')]"));
        sortBy.click();

        WebElement dropDown = driver.findElement(By.xpath("//select[@name='products-orderby']"));
        dropDown.click();
        Thread.sleep(3000);

        WebElement wishList = driver.findElement(By.xpath(" //div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//input[3]"));
        wishList.click();
        Thread.sleep(3000);
        WebElement wishListText = driver.findElement(By.xpath("//p[@class='content']"));
        String expectedText = "The product has been added to your wishlist";
        String actualText = wishListText.getText();
        Assert.assertEquals(expectedText,actualText);

       System.out.println("Expected message on Page : " + expectedText);
        System.out.println("Actual message on Page : " + wishListText.getText());

    }
    @After
    public void closeBrowser(){

        driver.quit();
    }

}
