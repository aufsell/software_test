import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class LogoutTest {
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
    public void logout() {
        for (WebDriver driver : drivers) {
            driver.get("https://hosting.timeweb.ru/login");
            driver.manage().window().setSize(new Dimension(1209, 964));

            driver.findElement(By.name("username")).click();
            driver.findElement(By.name("username")).sendKeys("cs41060");
            driver.findElement(By.xpath("//div[@id='react-tabs-1']/form")).click();
            driver.findElement(By.name("password")).click();
            driver.findElement(By.name("password")).sendKeys("L#O7o47#TTu!");
            driver.findElement(By.xpath("//p[contains(text(),'Войти в панель управления')]")).click();
            driver.findElement(By.xpath("//div[@id='main-header']/header/div/div[3]/button/p")).click();
            driver.findElement(By.xpath("//div[@id='main-header']/header/div/div[3]/ul/li/a")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            boolean isPageLoaded = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//div[@id='layout-pjax']/div[2]/div[2]/header/div/div[2]/p[2]"), "Wise Trinculo"));

            assertTrue(isPageLoaded, "Текст на странице после входа не найден");

            driver.findElement(By.xpath("//div[@id='main-header']/header/div/div[3]/button/p")).click();
            driver.findElement(By.xpath("//a[contains(text(),'Выйти')]")).click();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isPageLogin = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//h2[contains(.,'Вход в аккаунт')]"), "Вход в аккаунт"));
            assertTrue(isPageLogin, "Не страница входа");

            driver.close();
        }
    }
}
