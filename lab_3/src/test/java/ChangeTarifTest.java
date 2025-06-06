import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ChangeTarifTest {
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
  public void changetarif() {
    for (WebDriver driver : drivers) {
      driver.get("https://hosting.timeweb.ru/login");

      driver.findElement(By.name("username")).click();
      driver.findElement(By.name("username")).sendKeys("cs41060");
      driver.findElement(By.xpath("//div[@id='react-tabs-1']/form")).click();
      driver.findElement(By.name("password")).click();
      driver.findElement(By.name("password")).sendKeys("L#O7o47#TTu!");
      driver.findElement(By.xpath("//p[contains(text(),'Войти в панель управления')]")).click();

      driver.findElement(By.xpath("//a[contains(text(),'Улучшить тариф')]")).click();
      driver.findElement(By.xpath("//button[@id='tariff_wrapper-button']/span")).click();
      driver.findElement(By.xpath("//div[contains(.,'Optimo+')]")).click();
      driver.findElement(By.xpath("//button[@id='save']/span")).click();
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      Alert alert = wait.until(ExpectedConditions.alertIsPresent());
      String alertText = alert.getText();
      assertTrue(alertText.contains("Тарифный план успешно изменен"), "Ожидался alert с текстом 'Тарифный план успешно изменен'");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      alert.accept();
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      driver.findElement(By.xpath("//button[@id='tariff_wrapper-button']/span")).click();
      driver.findElement(By.xpath("//div[contains(.,'Year+')]")).click();
      driver.findElement(By.xpath("//button[@id='save']/span")).click();
      driver.close();
    }
  }
}
