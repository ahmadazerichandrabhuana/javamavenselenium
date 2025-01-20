import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver webDriver;
    By textHeaderLogin = By.xpath("//div[@class=\"login_logo\"]");
    By inputUsername = By.xpath("//input[@data-test=\"username\"]");
    By inputPassword = By.xpath("//input[@data-test=\"password\"]");
    By buttonLogin = By.xpath("//input[@data-test=\"login-button\"]");
    By iconErrorUsername = By.xpath("//input[@data-test=\"username\"]/following-sibling::*[name()=\"svg\"][contains(@class, \"error_icon\")]");
    By iconErrorPassword = By.xpath("//input[@data-test=\"password\"]/following-sibling::*[name()=\"svg\"][contains(@class, \"error_icon\")]");
    By textHeaderError = By.xpath("//h3[@data-test=\"error\"]");
    By buttonError = By.xpath("//button[@data-test=\"error-button\"]");
    By iconButtonError = By.xpath("//button[@data-test=\"error-button\"]/*[name()=\"svg\"]");

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @Step("Verify Login Page opened OK")
    public void verifyLoginPageOK(){
        Assert.assertFalse(webDriver.findElements(textHeaderLogin).isEmpty());
        Assert.assertFalse(webDriver.findElements(inputUsername).isEmpty());
        Assert.assertFalse(webDriver.findElements(inputPassword).isEmpty());
        Assert.assertFalse(webDriver.findElements(buttonLogin).isEmpty());
        Assert.assertEquals(webDriver.findElement(textHeaderLogin).getText(), "Swag Labs");
        Assert.assertEquals(webDriver.findElement(inputUsername).getDomAttribute("placeholder"), "Username");
        Assert.assertEquals(webDriver.findElement(inputPassword).getDomAttribute("placeholder"), "Password");
        Assert.assertEquals(webDriver.findElement(buttonLogin).getDomAttribute("value"), "Login");
    }

    @Step("Verify Login Page opened OK but the Test Case is failed")
    public void verifyLoginPageFailed(){
        Assert.assertFalse(webDriver.findElements(textHeaderLogin).isEmpty());
        Assert.assertFalse(webDriver.findElements(inputUsername).isEmpty());
        Assert.assertFalse(webDriver.findElements(inputPassword).isEmpty());
        Assert.assertFalse(webDriver.findElements(buttonLogin).isEmpty());
        Assert.assertEquals(webDriver.findElement(textHeaderLogin).getText(), "Swag Labs");
        Assert.assertEquals(webDriver.findElement(inputUsername).getDomAttribute("placeholder"), "Username");
        Assert.assertEquals(webDriver.findElement(inputPassword).getDomAttribute("placeholder"), "Password");
        Assert.assertEquals(webDriver.findElement(buttonLogin).getDomAttribute("value"), "Logins");
    }

    @Step("Click Login Button")
    public void clickLoginButton(){
        webDriver.findElement(buttonLogin).click();
    }

    @Step("Verify error on field Username")
    public void verifyErrorOnUsername(){
        Assert.assertFalse(webDriver.findElements(iconErrorUsername).isEmpty());
    }

    @Step("Verify error on field Password")
    public void verifyErrorOnPassword(){
        Assert.assertFalse(webDriver.findElements(iconErrorPassword).isEmpty());
    }

    @Step("Verify error \"Username is required\"")
    public void verifyErrorUsernameIsRequired(){
        Assert.assertFalse(webDriver.findElements(textHeaderError).isEmpty());
        Assert.assertFalse(webDriver.findElements(buttonError).isEmpty());
        Assert.assertFalse(webDriver.findElements(iconButtonError).isEmpty());
        Assert.assertEquals(webDriver.findElement(textHeaderError).getText(), "Epic sadface: Username is required");
    }

    @Step("Input into Username field")
    public void inputToUsername(String text){
        webDriver.findElement(inputUsername).clear();
        webDriver.findElement(inputUsername).sendKeys(text);
    }

    @Step("Input into Password field")
    public void inputToPassword(String text){
        webDriver.findElement(inputPassword).clear();
        webDriver.findElement(inputPassword).sendKeys(text);
    }

    @Step("Verify error \"Username and Password do not match\"")
    public void verifyErrorUsernameAndPasswordDoNotMatch(){
        Assert.assertFalse(webDriver.findElements(textHeaderError).isEmpty());
        Assert.assertFalse(webDriver.findElements(buttonError).isEmpty());
        Assert.assertFalse(webDriver.findElements(iconButtonError).isEmpty());
        Assert.assertEquals(webDriver.findElement(textHeaderError).getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Step("Verify Login Page is not shown anymore")
    public void verifyLoginPageIsNotShownAnymore(){
        Assert.assertTrue(webDriver.findElements(textHeaderLogin).isEmpty());
        Assert.assertTrue(webDriver.findElements(inputUsername).isEmpty());
        Assert.assertTrue(webDriver.findElements(inputPassword).isEmpty());
        Assert.assertTrue(webDriver.findElements(buttonLogin).isEmpty());
    }
}
