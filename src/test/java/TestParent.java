import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.TakesScreenshot;
import org.yaml.snakeyaml.Yaml;

public class TestParent {
    WebDriver webDriver;
    ChromeOptions options;
    static Yaml yaml;
    static InputStream inputStream;
    static Map<String, Object> yamlData;

    public void loadYaml() {
        yaml = new Yaml();
        try {
            inputStream = new FileInputStream("config.yaml");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        yamlData = yaml.load(inputStream);
    }

    @BeforeSuite
    public void startDataPreparation() throws IOException {
        FileUtils.deleteDirectory(new File("allure-results"));
        loadYaml();
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected void startTestCase() {
        options = new ChromeOptions();
//        options.addArguments("--headless"); // un-comment to make it headless
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get(yamlData.get("SauceDemo_URL").toString());
    }

    @AfterMethod
    public void closeTestCase(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot scrShot =((TakesScreenshot) this.webDriver);
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("Screenshot", Files.newInputStream(SrcFile.toPath()));
        }
        webDriver.close();
    }

    @AfterSuite
    public void addAllureInformation() {
        webDriver.quit();
        Properties allure = new Properties();
        allure.setProperty("Platform", "Web");
        allure.setProperty("Library", "Selenium + TestNG (for Assertion)");
        allure.setProperty("Code by", "Ahmad Azeri Chandra Bhuana");
        try {
            File file = new File("./allure-results/environment.properties");
            FileOutputStream fileOut = new FileOutputStream(file);
            allure.store(fileOut, "Allure Report Environment");
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}