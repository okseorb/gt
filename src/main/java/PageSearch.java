import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageSearch {
    WebDriver driver;

    By tagH1 = By.xpath("//h1");
    public PageSearch(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeadingText(){
        return driver.findElement(tagH1).getText();
    }
}
