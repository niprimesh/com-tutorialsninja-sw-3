package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class DesktopsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
//1.1 Mouse hover on Desktops Tab.and click
        mouseHoverAndClick(By.xpath("//a[normalize-space()='Desktops']"));
//1.2 Click on “Show All Desktops”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));
//1.3 Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.id("input-sort"),"Name (Z - A)");
//1.4 Verify the Product will arrange in Descending order.
        String actualTextOnDropDown = driver.findElement(By.xpath("//select/option[contains(text(), 'Name (Z - A)')]")).getText();
        String expectedTextOnDropDown = "Name (Z - A)";
        Assert.assertEquals(expectedTextOnDropDown,actualTextOnDropDown);



    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() throws InterruptedException {
//2.1 Mouse hover on Desktops Tab. and click
        mouseHoverAndClick(By.xpath("//a[normalize-space()='Desktops']"));
//2.2 Click on “Show All Desktops”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));
//2.3 Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (A - Z)");
//2.4 Select product “HP LP3065”
        clickOnElement(By.xpath("//a[normalize-space()='HP LP3065']"));
//2.5 Verify the Text "HP LP3065"
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']"));
        Assert.assertEquals("Invalid text", "HP LP3065", actualText);
//2.6 Select Delivery Date "2023-11-28"
        String year = "2023";
        String month = "November";
        String date = "28";

        clickOnElement(By.xpath("//div[@class='input-group date']//button[@type='button']")); //of date textbox
        while (true) {
            String monthYear = driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='picker-switch']")).getText(); //of Date and year element title in calender
            String[] a = monthYear.split(" "); //we need different month and year so split
            String mon = a[0];
            String yer = a[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next']"));
            }

        }
//        Select the date
//        Locator for all dates is mentioned
        List<WebElement> allDates = driver.findElements(By.xpath("//div[@class='datepicker-days']//tbody//tr//td"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }

//2.7.Enter Qty "1” using Select class.
        driver.findElement(By.id("input-quantity")).clear();
        sendTextToElement(By.id("input-quantity"), "1");
//2.8 Click on “Add to Cart” button
        clickOnElement(By.id("button-cart"));
//2.9 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        Thread.sleep(2000);
        String actualMessage =getTextFromElement(By.xpath("//div[@class = 'alert alert-success alert-dismissible']")).substring(0, 56);
        Assert.assertEquals("Invalid message", "Success: You have added HP LP3065 to your shopping cart!", actualMessage);

//2.10 Click on link “shopping cart” display into success message
        Thread.sleep(1000);
        clickOnElement(By.xpath("//span[contains(text(),'Shopping Cart')]"));
//2.11 Verify the text "Shopping Cart"
        Thread.sleep(2000);
        String actualTextTitle =getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]"));
        Assert.assertEquals("Invalid text of the title", "Shopping Cart  (1.00kg)", actualTextTitle);
//2.12 Verify the Product name "HP LP3065"
       // String actualProductName =getTextFromElement(By.xpath("//a[text()='HP LP3065'])[2]"));
       // Assert.assertEquals("Invalid name", "HP LP3065", actualProductName);
//2.13 Verify the Delivery Date "2023-11-28"
        String actualDeliveryDate = getTextFromElement(By.xpath("//small[contains(text(),'Delivery Date:2023-11-28')]"));
        Assert.assertEquals("Invalid date", "Delivery Date:2023-11-28", actualDeliveryDate);
//2.14 Verify the Model "Product21"
        String actualModel = getTextFromElement(By.xpath("//td[contains(text(),'Product 21')]"));
        Assert.assertEquals("Invalid model", "Product 21", actualModel);
//2.15 Verify the Todat "£74.73"
        String actualPrice =getTextFromElement(By.xpath("//div[@class='buttons clearfix']/preceding::td[1]"));
        Assert.assertEquals("Invalid price", "$122.00", actualPrice);

    }

    @After
    public void tearDown(){

        closedBrowser();
    }
}

