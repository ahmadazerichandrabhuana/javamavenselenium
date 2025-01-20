import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductsPage {
    WebDriver webDriver;
    By textProductsTitle = By.xpath("//span[@data-test=\"title\"]");

    public ProductsPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @Step("Verify Current Page opened is Product Page")
    public void verifyProductsPageOK(String baseUrl){
        Assert.assertFalse(webDriver.findElements(textProductsTitle).isEmpty());
        Assert.assertEquals(webDriver.findElement(textProductsTitle).getText(), "Products");
        Assert.assertEquals(webDriver.getCurrentUrl(), baseUrl + "inventory.html");
    }
}
