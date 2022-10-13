import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By signInButton = By.xpath("//div[@class='right-icons pull-right wb']//a[@title='Мой кабинет']");
    private By formSignInH2 = By.xpath("//div[@class='form_head']//h2[text()='Личный кабинет']");
    private By formSignInLogin = By.xpath("//div[@class='form_body']//input[@id='USER_LOGIN_POPUP']");
    private By formSignInPassword = By.xpath("//div[@class='form_body']//input[@id='USER_PASSWORD_POPUP']");
    private By formSignInButtonEnter = By.xpath("//div[@class='form_footer']//button[@name='Login1']");
    private By formSignInButtonRegister = By.xpath("//div[@class='form_footer']//a[text()='Регистрация']");
    private By formSignInLinkForgotPassword = By.xpath("//div[@class='form_footer']//a[text()='Забыли пароль?']");

    private By menuLinkKatalog = By.xpath("//div[@class='menu-only']//td[@class='menu-item dropdown catalog wide_menu']//a[@href='/catalog/']");
    private By menuAllLinkSubKatalog = By.xpath("//div[@class='dropdown-menu with_right_block BANNER']//a[@class='noborder colored_theme_svg']");

    private By menuLinkKatalogGeotextil = By.xpath("//div[@class='dropdown-menu with_right_block BANNER']//li//a/span[text()='Геотекстиль']/..");
    private By menuLinkKatalogElki = By.xpath("//div[@class='dropdown-menu with_right_block BANNER']//li//a/span[text()='Елки и сосны искусственные']/..");

    //Как составить xPath по тексту "Акции"?
    private By menuLinkPromotions = By.xpath("//div[@class='menu-only']//td[@class='menu-item']//a[@href='/sale/']");
    private By menuLinkArticles = By.xpath("//div[@class='menu-only']//td[@class='menu-item']//a//preceding-sibling::div[contains(text(), 'Статьи')]");
    private By menuLinkNews = By.xpath("//div[@class='menu-only']//td[@class='menu-item']//a//preceding-sibling::div[contains(text(), 'Новости')]");
    private By menuLinkDeliveryAndPayment = By.xpath("//div[@class='menu-only']//td[@class='menu-item']//a//preceding-sibling::div[contains(text(), 'Доставка и оплата')]");


    private By callBackFormButton = By.xpath("//div[@class='phone-block blocks']//span[@data-name='callback']");
    private By formCallBackH2 = By.xpath("//div[@class='form CALLBACK  ']/div[@class='form_head']/h2");
    private By formCallBackName = By.xpath("//div[@class='form CALLBACK  ']//input[@data-sid='CLIENT_NAME']");
    private By formCallBackNameError = By.xpath("//div[@class='form CALLBACK  ']//input[@class='inputtext']/../label[@class='error']");
    private By formCallBackPhone = By.xpath("//div[@class='form CALLBACK  ']//input[@data-sid='PHONE']");
    private By formCallBackPhoneError = By.xpath("//div[@class='form CALLBACK  ']//input[@class='phone']/../label[@class='error']");
    private By formCallBackLicenses = By.xpath("//div[@class='form CALLBACK  ']//input[@id='licenses_popup']");
    private By formCallBackLicensesError = By.xpath("//div[@class='form CALLBACK  ']//label[@id='licenses_popup-error']");
    private By formCallBackButtonSend = By.xpath("//div[@class='form CALLBACK  ']//button");

    private By searchField = By.xpath("//div[@id='title-search_fixed']//form[@class='search']//input");
    private By searchFieldButton = By.xpath("//div[@id='title-search_fixed']//form[@class='search']//button");
    private By widgetChat = By.xpath("//body[@id='main']//div[@dir='ltr']");

    private By linkHeaderPhone = By.xpath("//div[@class='phone with_dropdown no-icons']/a");
    private By linkAllHeaderPhonesArray = By.xpath("//div[@class='phone with_dropdown no-icons']//div[@class='more_phone']/a");


    public PageKatalog clickMenuKatalog() {
        driver.findElement(menuLinkKatalog).click();
        return new PageKatalog(driver);
    }
    public PageMenuDeliveryAndPayment clickDeliveryAndPayment() {
        driver.findElement(menuLinkDeliveryAndPayment).click();
        return new PageMenuDeliveryAndPayment(driver);
    }


    public void clickCallBackButton() {
        driver.findElement(callBackFormButton).click();
    }
    public MainPage typeCallBackFormName(String username) {
        driver.findElement(formCallBackName).sendKeys(username);
        return this;
    }
    public MainPage typeCallBackFormPhone(String phone) {
        driver.findElement(formCallBackPhone).sendKeys(phone);
        return this;
    }
    public MainPage sendCallBackForm() {
        driver.findElement(formCallBackButtonSend).click();
        return this;
    }
    public String getCallBackFormH2() {
        return driver.findElement(formCallBackH2).getText();
    }
    public String getCallBackPhoneErrorText() {
        return driver.findElement(formCallBackPhoneError).getText();
    }
    public MainPage callBack(String username, String phone) {
        this.clickCallBackButton();
        this.typeCallBackFormName(username);
        this.typeCallBackFormPhone(phone);
        this.sendCallBackForm();
        return this;
    }

    public PageSearch searchFieldInputText(String text) {
        driver.findElement(searchField).sendKeys(text);
        return new PageSearch(driver);
    }

    public PageSearch searchFieldInputText() {
        driver.findElement(searchFieldButton).click();
        return new PageSearch(driver);
    }

    public PageSearch searchText(String text) {
        this.driver.findElement(searchField).sendKeys(text);
        this.driver.findElement(searchFieldButton).click();
        return new PageSearch(driver);
    }

    public boolean isWidgetChatDisplayed() {
        if (this.driver.findElement(widgetChat).isDisplayed()) {
            System.out.println("Element is Visible (is Displayed)");
            return true;
        } else {
            System.out.println("Element is InVisible");
            return false;
        }
    }
    public boolean isWidgetChatDisplayedWait(Integer sec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        WebElement elWait = wait.until(ExpectedConditions.visibilityOfElementLocated(widgetChat));
        if (elWait.isDisplayed()) {
            System.out.println("Element is Visible (is Displayed)");
            return true;
        } else {
            System.out.println("Element is InVisible");
            return false;
        }
    }

    public boolean isAllPhonesShow() {
        Actions action = new Actions(this.driver);
        WebElement firstHeaderPhone = this.driver.findElement(linkHeaderPhone);
        WebElement lastPhone = null;
        action.moveToElement(firstHeaderPhone).build().perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> allPhones = this.driver.findElements(linkAllHeaderPhonesArray);
        if (allPhones.size() > 1) {
            System.out.println("Всего телефонов в Header: " + allPhones.size() + "шт.");
            System.out.println("А Именно:");
            for (WebElement phone : allPhones) {
                System.out.println(phone.getText());
                lastPhone = phone;
            }
            if (lastPhone.isDisplayed()) {
                System.out.println("Last phone is Displayed");
                return true;
            } else {
                System.out.println("Last phone is not Displayed");
                return false;
            }
        } else {
            System.out.println("Only 1 phone found.");
            return false;
        }

    }    public boolean isAllmenuSubKatalogMore10() {
        Actions action = new Actions(this.driver);
        WebElement menuKatalog = this.driver.findElement(menuLinkKatalog);
        WebElement lastSubKatalog = null;
        action.moveToElement(menuKatalog).build().perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> allSubKatalogs = this.driver.findElements(menuAllLinkSubKatalog);
        Integer i = 1;
        if (allSubKatalogs.size() > 10) {
            System.out.println("Всего подкатегорий в меню: " + allSubKatalogs.size() + "шт.");
            System.out.println("А Именно:");
            for (WebElement subKatalog : allSubKatalogs) {
                if (subKatalog.isDisplayed()) {
                    System.out.println(i + " SubKatalog is Displayed: " + subKatalog.getAttribute("title"));
                    i++;
                } else {
                    System.out.println(i + " SubKatalog is not Displayed: " + subKatalog.getAttribute("title"));
                    i++;
                }
            }
            return true;
        } else {
            System.out.println("Too little of categories.");
            return false;
        }

    }
}