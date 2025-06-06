package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Set;

public class LoadCookies {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.get("https://hosting.timeweb.ru/");

        // Загружаем cookies
        Set<Cookie> cookies;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cookies.data"))) {
            cookies = (Set<Cookie>) ois.readObject();
        }

        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
            System.out.println(cookie);
        }

        // Обновляем страницу — теперь ты должен быть авторизован
        driver.navigate().refresh();

        Thread.sleep(500000); // Просто для просмотра результата
        System.out.println("Авторизация восстановлена через cookies.");
        driver.quit();
    }
}