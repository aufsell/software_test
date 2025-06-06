import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class BuyDomenAlternativeTest {
    private final WebDriver[] drivers = new WebDriver[2];
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        drivers[0] = new FirefoxDriver();
        drivers[1] = new ChromeDriver();
        drivers[0].manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        drivers[1].manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void buydomen() {
        for (WebDriver driver : drivers) {
            driver.get("https://hosting.timeweb.ru/login");
            driver.findElement(By.xpath("//input[@name='password']")).click();
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("L#O7o47#TTu!");
            driver.findElement(By.xpath("//input[@name='username']")).click();
            driver.findElement(By.xpath("//input[@name='username']")).sendKeys("cs41060");
            driver.findElement(By.xpath("//div[@id='log-in-layout']/section")).click();
            driver.findElement(By.xpath("//div[@id='react-tabs-1']/form/div[4]/button/p")).click();
            driver.findElement(By.xpath("//a[contains(text(),'Купить домен')]")).click();
            driver.findElement(By.xpath("//input[@name='']")).click();
            String randomDomain = "site";
            driver.findElement(By.xpath("//input[@name='']")).sendKeys(randomDomain);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean isErrorMessage = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//div[@id='default-layout']/div[3]/main/div[2]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[2]"),
                    "Домен уже зарегистрирован. Выберите другой домен."));
            assertTrue(isErrorMessage, "Некорректное сообщение об ошибке");
            driver.close();
        }
    }
}
