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

public class ReportTest {
    private final WebDriver[] drivers = new WebDriver[2];

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
        drivers[1] = new FirefoxDriver();
        drivers[0] = new ChromeDriver();
        drivers[0].manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        drivers[1].manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void createsite() {
        for (WebDriver driver : drivers) {
            driver.get("https://hosting.timeweb.ru/login");
            driver.findElement(By.xpath("//input[@name='password']")).click();
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("L#O7o47#TTu!");
            driver.findElement(By.xpath("//input[@name='username']")).click();
            driver.findElement(By.xpath("//input[@name='username']")).sendKeys("cs41060");
            driver.findElement(By.xpath("//div[@id='react-tabs-1']/form/div[4]/button/p")).click();
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            driver.findElement(By.xpath("//div[4]/span/span[2]")).click();
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("d");
            driver.findElement(By.xpath("//input[@name='url']")).sendKeys("d");
            driver.findElement(By.xpath("//button[contains(.,'Отправить')]")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean bugSend = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//li[contains(.,'Ваше сообщение успешно отправлено')]"),
                    "Ваше сообщение успешно отправлено"));
            assertTrue(bugSend, "Баг не отправился");

            driver.close();
        }
    }
}
