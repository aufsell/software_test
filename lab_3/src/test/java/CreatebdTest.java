import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CreatebdTest {
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
  public void createbd() {
    for (WebDriver driver : drivers) {
    driver.get("https://hosting.timeweb.ru/login");

    driver.findElement(By.xpath("//input[@name='username']")).sendKeys("cs41060");

    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("L#O7o47#TTu!");

    driver.findElement(By.xpath("//div[@id='react-tabs-1']/form/div[4]/button/p")).click();
      try{
        Thread.sleep(3000);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    driver.findElement(By.xpath("//span[contains(.,'Базы данных')]")).click();
      try{
        Thread.sleep(3000);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    driver.findElement(By.xpath("//div[@id='create_db']/div[2]/div/span")).click();
    driver.findElement(By.xpath("//input[@id='name_db']")).sendKeys("fff");
    driver.findElement(By.xpath("//input[@id='pswd_db']")).sendKeys("fff");

    driver.findElement(By.xpath("//button[@id='create_base']/span/span")).click();

    try{
      Thread.sleep(3000);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      boolean isPaymentPage = wait.until(ExpectedConditions.textToBePresentInElementLocated(
              By.xpath("//tr[@id='trow_0']/td[2]/span"), "cs41060_fff"));
      assertTrue(isPaymentPage, "Не создалась");

    driver.findElement(By.xpath("//input[@name='cb']")).click();
    driver.findElement(By.xpath("//div[@id='table-actions']/div/span[2]")).click();


    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    assertThat(alert.getText(), is("Вы действительно хотите удалить базы данных?"));


    alert.accept();
    driver.close();
  }
}}
