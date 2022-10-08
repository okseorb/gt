import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageKatalog {
    WebDriver driver;

    public PageKatalog(WebDriver driver) {
        this.driver = driver;
    }

    By tagH1 = By.xpath("//h1");
    By sectionNames = By.xpath("//div[@class='item_block lg col-lg-20 col-md-4 col-xs-6']//td[@class='section_info toggle']//a//span");
    By linkCatGeotextil = By.xpath("//div[@class='item_block lg col-lg-20 col-md-4 col-xs-6']//td[@class='section_info toggle']//a//span[text()='Геотекстиль']/..");
    By linkCatElkiISosny = By.xpath("//div[@class='item_block lg col-lg-20 col-md-4 col-xs-6']//td[@class='section_info toggle']//a//span[text()='Елки и сосны искусственные']/..");





}
