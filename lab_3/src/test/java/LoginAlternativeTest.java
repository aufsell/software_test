import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginAlternativeTest {
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
  public void testAlternative() {
    for (WebDriver driver : drivers) {
    driver.get("https://hosting.timeweb.ru/login");


    driver.findElement(By.xpath("//input[@name='username']")).click();
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys("123");


    driver.findElement(By.xpath("//input[@name='password']")).click();
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123");


    driver.findElement(By.xpath("//div[@id='react-tabs-1']/form/div[4]/button/p")).click();
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    boolean isErrorDisplayed = wait.until(ExpectedConditions.textToBePresentInElementLocated(
            By.xpath("//div[@id='log-in-layout']/section/div/div/div/div/div/p"),
            "Логин или пароль указаны неверно. Попробуйте ещё раз"));


    assertTrue(isErrorDisplayed, "Текст ошибки на странице не найден");
    driver.close();
  }}
}
