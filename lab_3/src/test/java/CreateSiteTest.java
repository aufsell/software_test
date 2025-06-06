import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateSiteTest {
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
  public void createsite() {
    for (WebDriver driver : drivers) {
      driver.get("https://hosting.timeweb.ru/login");
      driver.findElement(By.xpath("//input[@name='password']")).click();
      driver.findElement(By.xpath("//input[@name='password']")).sendKeys("L#O7o47#TTu!");
      driver.findElement(By.xpath("//input[@name='username']")).click();
      driver.findElement(By.xpath("//input[@name='username']")).sendKeys("cs41060");
      driver.findElement(By.xpath("//div[@id='react-tabs-1']/form/div[4]/button/p")).click();
      driver.findElement(By.xpath("//div[@id='default-layout']/div[2]/main/div[3]/div/a[3]")).click();
      driver.findElement(By.xpath("//div[@id='default-layout']/div[2]/main/div[2]/div/div[2]/div/button")).click();
      driver.findElement(By.xpath("//input[@name='directoryName']")).click();
      driver.findElement(By.xpath("//input[@name='directoryName']")).sendKeys("ss");
      driver.findElement(By.xpath("//button[contains(.,'Сохранить')]")).click();

      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      driver.findElement(By.xpath("//tr[2]/td[2]/div/div/div[2]")).click();

      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      boolean isSiteCreated = wait.until(ExpectedConditions.textToBePresentInElementLocated(
              By.xpath("//div[@id='default-layout']/div[2]/main/div[2]/div[2]/div/h1"), "Настройки сайта ss"));
      assertTrue(isSiteCreated, "Сайт не создался");

      driver.findElement(By.xpath("//div[@id='default-layout']/div[2]/main/div[2]/div/button")).click();
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      driver.findElement(By.xpath("//div[@id='default-layout']/div[2]/main/div[2]/section/div/table/tbody/tr[2]/td/label/div/div")).click();
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      driver.findElement(By.xpath("//div[@id='default-layout']/div[2]/main/div[2]/section/div/table/thead/tr/th[2]/button/span")).click();
      driver.findElement(By.xpath("//div[2]/label/div/div")).click();
      driver.findElement(By.xpath("//button[2]")).click();
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      driver.close();
    }
  }
}
