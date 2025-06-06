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
import java.util.Random;

public class BuyDomenTest {
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
      String randomDomain = generateRandomString(24);
      driver.findElement(By.xpath("//input[@name='']")).sendKeys(randomDomain);
      try {
        Thread.sleep(11000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      driver.findElement(By.xpath("//div[@id='default-layout']/div[3]/main/div[2]/div/div/div/div[2]/div/div/div/div/div/button")).click();
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      driver.findElement(By.xpath("//div[@id='default-layout']/div[3]/main/div[2]/div/div/div/div[2]/footer/button")).click();
      try{
        Thread.sleep(10000);
      }
      catch (InterruptedException e){
        e.printStackTrace();
      }
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      boolean isPaymentPage = wait.until(ExpectedConditions.textToBePresentInElementLocated(
              By.xpath("//h2[contains(.,'Оплата')]"), "Оплата"));
      assertTrue(isPaymentPage, "Не дошли до оплаты");

      driver.close();
    }
  }
  private String generateRandomString(int length) {
    String characters = "abcdefghijklmnopqrstuvwxyz"; // Разрешённые символы
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(characters.length());
      sb.append(characters.charAt(index));
    }
    return sb.toString();
  }
}
