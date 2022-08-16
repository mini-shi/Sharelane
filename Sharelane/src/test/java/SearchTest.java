import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SearchTest {

    @Test
    public void searchByBookTitle() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        String bookTitleToSearch = "Gitanjali";
        driver.findElement(By.name("keyword")).sendKeys(bookTitleToSearch);
        driver.findElement(By.cssSelector("[value=Search]")).click();
        String bookTitle = driver.findElement(By.xpath("//table//p[2]")).getText();
        assertEquals(bookTitle, bookTitleToSearch, "Поиск не нашёл указанную книгу по названию");
        driver.quit();
    }

    @Test
    public void searchByBookAuthor() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        String bookAuthorToSearch = "W.Somerset Maugham";
        driver.findElement(By.name("keyword")).sendKeys(bookAuthorToSearch);
        driver.findElement(By.cssSelector("[value=Search]")).click();
        String bookAuthor = driver.findElement(By.xpath("//table[2]//p[1]//b")).getText();
        assertEquals(bookAuthor, bookAuthorToSearch, "Поиск не нашёл книгу по указанному автору");
        driver.quit();
    }

    @Test
    public void emptySearch() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.name("keyword")).sendKeys("");
        driver.findElement(By.cssSelector("[value=Search]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error. No keyword is provided", "Неверное сообщение об ошибке при пустом поле ввода поиска");
        driver.quit();
    }
}