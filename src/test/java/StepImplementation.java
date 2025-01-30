
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StepImplementation {

    @Step("Open browser and navigate to <url>")
    public void openBrowserAndNavigate(String url) {
        Driver.initializeDriver();  // Start browser
        Driver.openUrl(url);        // Navigate to URL
    }

    @Step("Login using the standard user credentials")
    public void standardLogin() {
        WebDriver driver = Driver.getDriver();
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();
    }

    @Step("Verify if inventory page loads")
    public void verifyInventoryLoads() {
        WebDriver driver = Driver.getDriver();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
    }

    @Step("Validate the product sorting functionality")
    public void validateSorting() {
        WebDriver driver = Driver.getDriver();
        sortingAZ(driver);
        sortingZA(driver);
        sortingPriceLowToHigh(driver);
        sortingPriceHighToLow(driver);


    }
    public void sortingAZ(WebDriver driver) {
        WebElement filterButton = driver.findElement(By.className("product_sort_container"));
        Select dropdown = new Select(filterButton);
        dropdown.selectByIndex(0);

        List<String> currentProductNamesList = getCurrentProductNamesList(driver);
        List<String> sortedAZList = new ArrayList<>(currentProductNamesList);
        Collections.sort(sortedAZList);
        //System.out.println(sortedAZList);
        Assert.assertEquals(currentProductNamesList, sortedAZList);

    }
    public void sortingZA(WebDriver driver) {
        WebElement filterButton = driver.findElement(By.className("product_sort_container"));
        Select dropdown = new Select(filterButton);
        dropdown.selectByIndex(1);
        List<String> currentProductNamesList = getCurrentProductNamesList(driver);
        List<String> sortedZAList = new ArrayList<>(currentProductNamesList);
        Collections.sort(sortedZAList, Collections.reverseOrder());
        //System.out.println(sortedZAList);
        Assert.assertEquals(currentProductNamesList, sortedZAList);

    }
    public void sortingPriceLowToHigh(WebDriver driver) {
        WebElement filterButton = driver.findElement(By.className("product_sort_container"));
        Select dropdown = new Select(filterButton);
        dropdown.selectByIndex(2);
        List<Double> currentPriceList = getCurrentProductPricesList(driver);
        List<Double> sortedPriceList = new ArrayList<>(currentPriceList);
        Collections.sort(sortedPriceList);
        System.out.println("Current Price List");
        System.out.println(currentPriceList);
        System.out.println("Sorted Price List (expected)");
        System.out.println(sortedPriceList);
        Assert.assertEquals(currentPriceList, sortedPriceList);
    }
    public void sortingPriceHighToLow(WebDriver driver) {
        WebElement filterButton = driver.findElement(By.className("product_sort_container"));
        Select dropdown = new Select(filterButton);
        dropdown.selectByIndex(3);
        List<Double> currentPriceList = getCurrentProductPricesList(driver);
        List<Double> sortedReversedPriceList = new ArrayList<>(currentPriceList);
        Collections.sort(sortedReversedPriceList, Collections.reverseOrder());

        System.out.println("Current Price List");
        System.out.println(currentPriceList);
        System.out.println("Sorted Reversed Price List (expected)");
        System.out.println(sortedReversedPriceList);
        Assert.assertEquals(currentPriceList, sortedReversedPriceList);
    }


    public List<String> getCurrentProductNamesList(WebDriver driver) {
        WebElement productDiv = driver.findElement(By.xpath("//*[@id='inventory_container']/div"));
        List<WebElement> productDivElements = productDiv.findElements(By.className("inventory_item_label"));
        List<String> productNamesList = new ArrayList<String>();
        for(WebElement i : productDivElements) {
            String name = i.findElement(By.tagName("a")).getText();
            //System.out.println(name);
            productNamesList.add(name);
        }
        return productNamesList;

    }

    public List<Double> getCurrentProductPricesList(WebDriver driver) {
        List<WebElement> productPriceElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> productPriceNumberList = new ArrayList<>();

        for(WebElement i : productPriceElements) {
            String price = i.getText();
            price = price.replace("$", "");
            double priceNumber = Double.parseDouble(price);
            System.out.println(priceNumber);
            productPriceNumberList.add(priceNumber);
        }

        return productPriceNumberList;
    }

    @Step("Sort products by <sort_option>")
    public void sortOptionTableProducts(String sort_option) {
        WebDriver driver = Driver.getDriver();
            switch (sort_option) {
                case "Name (A to Z)":
                    sortingAZ(driver);
                case "Name (Z to A)":
                    sortingZA(driver);
                case "Price (low to high)":
                    sortingPriceLowToHigh(driver);
                case "Price (high to low)":
                    sortingPriceHighToLow(driver);
        }
    }

    @Step("Verify products are sorted by <sort_criteria>")
    public void sortCriteriaTableProducts(String sort_criteria) {
        WebDriver driver = Driver.getDriver();
        switch (sort_criteria) {
            case "name":
                sortingAZ(driver);
            case "name_desc":
                sortingZA(driver);
            case "price":
                sortingPriceLowToHigh(driver);
            case "price_desc":
                sortingPriceHighToLow(driver);
        }
    }
    @Step("Close the browser")
    public void closeBrowser() {
        Driver.closeDriver();
    }
}
