import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class MainClassTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "c:\\Users\\usr\\IdeaProjects\\test-selen\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //задержка для ожидания элемента
        driver.manage().window().maximize();
        driver.get("https://");
        mainPage = new MainPage(driver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void searchTest(){
        PageSearch pageSearch = mainPage.searchText("оборудование");
        String heading = pageSearch.getHeadingText();
        System.out.println(heading);
        Assert.assertEquals("Поиск", heading);
    }
    @Test
    public void widgetIsDisplayedTest(){
       Assert.assertTrue( mainPage.isWidgetChatDisplayed());
    }
    @Test
    public void widgetIsDisplayedWait(){
       Assert.assertTrue(mainPage.isWidgetChatDisplayedWait(7));
    }

/*    @Test
    public void widgetIsDisplayedWithDelay5000Test(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(mainPage.isWidgetChatDisplayed());
    }*/
    @Test
    public void allHeaderPhonesOpenTest(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(mainPage.isAllPhonesShow());
    }
      @Test
    public void callBackHeaderFormOpenTest(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mainPage.clickCallBackButton();
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
          Assert.assertEquals("Заказать звонок", mainPage.getCallBackFormH2());
    }

    @Test
    public void callBackHeaderFormWrongPhoneTest(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mainPage.callBack("userTest","+37500123456" );
        Assert.assertEquals("Неверный формат", mainPage.getCallBackPhoneErrorText());
    }
    @Test
    public void subKatalogsMoreThan10(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(mainPage.isAllmenuSubKatalogMore10());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
