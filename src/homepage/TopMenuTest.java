package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    public void selectMenu(String menu) {
//    This method should click on the menu
        List<WebElement> elementList = driver.findElements(By.id("menu"));
        for (WebElement element : elementList) {
            if (element.getText().equalsIgnoreCase(menu)) {
                element.click();
                break;
            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() throws InterruptedException {
//1.1 Mouse hover on “Desktops” Tab and click
        mouseHoverAndClick(By.xpath("//a[normalize-space()='Desktops']"));
//1.2 call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("Show AllDesktops");
        clickOnElement(By.xpath("//a[contains(text(),'Show AllDesktops')]"));
//1.3 Verify the text ‘Desktops’
        Thread.sleep(2000);
        String actualText = getTextFromElement(By.xpath("//h2[normalize-space()='Desktops']"));
        Assert.assertEquals("Invalid text", "Desktops", actualText);

    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
//2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        mouseHoverAndClick(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
//2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");
        clickOnElement(By.xpath("//a[contains(text(),'Show AllLaptops & Notebooks')]"));
//2.3 Verify the text ‘Laptops & Notebooks’
        String actualText = getTextFromElement(By.xpath("//h2[normalize-space()='Laptops & Notebooks']"));
        Assert.assertEquals("Invalid text", "Laptops & Notebooks", actualText);

    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
//3.1 Mouse hover on “Components” Tab and click
        mouseHoverAndClick(By.xpath("//a[normalize-space()='Components']"));
//3.2 call selectMenu method and pass the menu = “Show All Components”
        selectMenu("Show AllComponents");
        clickOnElement(By.xpath("//a[contains(text(),'Show AllComponents')]"));
//3.3 Verify the text ‘Components’
        String actualText = getTextFromElement(By.xpath("//h2[normalize-space()='Components']"));
        Assert.assertEquals("Invalid text", "Components", actualText);
    }

    @After
    public void tearDown() {
        closedBrowser();
    }
}

