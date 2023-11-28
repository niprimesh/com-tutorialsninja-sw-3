package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class LaptopAndNotebooksTest  extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully(){
//1.1 Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverAndClick(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
//1.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[contains(text(),'Show AllLaptops & Notebooks')]"));
//1.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.id("input-sort"),"Price (High > Low)");
//1.4 Verify the Product price will arrange in High to Low order
        List<WebElement> productPrices = driver.findElements(By.xpath("//div[@class='row']//p//span"));
        for (WebElement productPrice:productPrices) {
            String actualPrice =productPrice.getText();
        }

        Assert.assertEquals("Invalid text", "");
    }
    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
//2.1 Mouse hover on Laptops & Notebooks Tab and click
        mouseHoverAndClick(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
//2.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[contains(text(),'Show AllLaptops & Notebooks')]"));
//2.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.id("input-sort"),"Price (High > Low)");
//2.4 Select Product “MacBook”
        clickOnElement(By.xpath("//a[contains(text(),'MacBook Pro')]"));
//2.5 Verify the text “MacBook”
        String actualText =getTextFromElement(By.xpath("//h1[contains(text(),'MacBook Pro')]"));
        Assert.assertEquals("Invalid text", "MacBook Pro", actualText);
//2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.id("button-cart"));
//2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        String actualMessage = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        Assert.assertEquals("Invalid message", "Success: You have added MacBook Pro to your shopping cart!\n" +
                "×", actualMessage);
//2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
//2.9 Verify the text "Shopping Cart"
        String actualTextTitle =getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]"));
        Assert.assertEquals("Invalid text of the title", "Shopping Cart  (0.00kg)", actualTextTitle);
//2.10 Verify the Product name "MacBook"
        String actualProductName =getTextFromElement(By.xpath("//small[contains(text(),'Reward Points: 800')]//preceding-sibling::a"));
        Assert.assertEquals("Invalid name", "MacBook Pro", actualProductName);
//2.11 Change Quantity "2"
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='input-group btn-block']/child::input")).clear();
        sendTextToElement(By.xpath("//div[@class='input-group btn-block']/child::input"),"2");
//2.12 Click on “Update” Tab
        clickOnElement(By.xpath("//button[@type='submit']"));
//2.13 Verify the message “Success: You have modified your shopping cart!”
        String actualModificationMsg = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        Assert.assertEquals("Invalid modification message", "Success: You have modified your shopping cart!\n" +
                "×", actualModificationMsg);
//2.14 Verify the Total £737.45
        String actualPrice =getTextFromElement(By.xpath("//td[contains(text(),'$2,000.00')]//following-sibling::td"));
        Assert.assertEquals("Invalid price", "$4,000.00", actualPrice);
//2.15 Click on “Checkout” button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
//2.16 Verify the text “Checkout”
        String checkoutText =getTextFromElement(By.xpath("//h1[text()='Checkout']"));
        Assert.assertEquals("Invalid text", "Checkout", checkoutText);
//2.17 Verify the Text “New Customer”
        Thread.sleep(2000);
        String actualNewCustomerTitle  = getTextFromElement(By.xpath("//div[@class='col-sm-6']/parent::div/child::div[1]/h2"));
        Assert.assertEquals("Invalid text", "New Customer", actualNewCustomerTitle);
//2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@value='guest']"));
//2.19 Click on “Continue” tab
        clickOnElement(By.id("button-account"));
//2.20 Fill the mandatory fields
        sendTextToElement(By.id("input-payment-firstname"), "Original");
        sendTextToElement(By.id("input-payment-lastname"), "Patel");
        sendTextToElement(By.id("input-payment-email"), "ogpatel@gmail.com" );
        sendTextToElement(By.id("input-payment-telephone"), "07435626624" );
        sendTextToElement(By.id("input-payment-address-1"), "13, Happy Home");
        sendTextToElement(By.id("input-payment-city"), "Ahmedabad");
        sendTextToElement(By.id("input-payment-postcode"), "300122");
        selectByVisibleTextFromDropDown(By.id("input-payment-country"), "India");
        selectByVisibleTextFromDropDown(By.id("input-payment-zone"),"Gujarat");
//2.21 Click on “Continue” Button
        Thread.sleep(2000);
        clickOnElement(By.id("button-guest"));
//2.22 Add Comments About your order into text area
        Thread.sleep(2000);
        clickOnElement(By.xpath("//textarea[@name='comment']"));
        sendTextToElement(By.xpath("//textarea[@name='comment']"),"give it to neighbor");
        Thread.sleep(2000);
        sendTextToElement(By.xpath("//textarea[@name='comment']"), "Please deliver it at the reception at delivery counter");
//2.23 Check the Terms & Conditions check box
        clickOnElement(By.id("button-shipping-method"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[@class='agree']/following-sibling::input[1]"));
//2.24 Click on “Continue” button
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='button-payment-method']"));
//2.25 Verify the message “Warning: Payment method required!”
        Thread.sleep(2000);
        clickOnElement(By.id("button-confirm"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[text()='Continue']"));
    }

    @After
    public void tearDown(){
        closedBrowser();
    }
}


