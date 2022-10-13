import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PParser {


    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.gecko.driver", "c:\\Users\\usr\\IdeaProjects\\test-selen\\drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //задержка для ожидания элемента
        driver.manage().window().maximize();


        driver.navigate().to("");
//        driver.navigate().to("/catalog/");
        String windowHandle1 = driver.getWindowHandle();

        By xPaginationNext = By.xpath("//a[@class='item icon-custom next']");
        By xAllSubCategories = By.xpath("//div[@class='subcategories']/a[@class='item']");
        List<WebElement> allSubCategoriesNames = driver.findElements(By.xpath("//div[@class='subcategories']/a[@class='item']/span[@class='name']"));
        List<WebElement> allSubCategories = driver.findElements(By.xpath("//div[@class='subcategories']/a[@class='item']"));
        List<String> strAllSubCategoriesLinks = new ArrayList<>();
        List<String> strAllSubCategoriesNames = new ArrayList<>();

        List<WebElement> allNames;
        List<WebElement> allPrices;
        String title = driver.findElement(By.xpath("//h1")).getText();

        // For Excel
        List<String> exName = new ArrayList<>();
        List<String> exPrice = new ArrayList<>();
        List<String> exTitle = new ArrayList<>();

//        System.out.println(allNames.get(1).getText());
//        System.out.println(allPrices.get(1).getText());
//        System.out.println("Size: " + allNames.size());


        try {
            for(WebElement cat:allSubCategories){
                strAllSubCategoriesLinks.add(cat.getDomProperty("href").toString());
                System.out.println("href=" + cat.getDomProperty("href"));
            }
            for (WebElement name : allSubCategoriesNames) {
                strAllSubCategoriesNames.add(name.getText());
                System.out.println("name=" + name.getText());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        String fileText = "";
        String subCatUrl = "";


        for (int i = 0; i < strAllSubCategoriesLinks.size(); i++) {

            subCatUrl = strAllSubCategoriesLinks.get(i);
            System.out.println("subCatUrl is: " + subCatUrl);

            driver.navigate().to(subCatUrl);



            String subCatName = strAllSubCategoriesNames.get(i);
            System.out.println(subCatName);

            fileText = fileText + subCatName + "\n";
            do {
                allNames = driver.findElements(By.xpath("//a[@class='name']"));
                allPrices = driver.findElements(By.xpath("//div[@class='price']"));
                System.out.println(subCatName + "allNames.size: " + allNames.size());
                if (allNames.size() > 0) {
                    for (int j = 0; j < allNames.size(); j++) {
                        String titleName = allNames.get(j).getText();
                        String titlePrice = allPrices.get(j).getText();

                        fileText = fileText + titleName + "," + titlePrice + "\n";

                        // For Excel
                        exName.add(titleName);
                        exPrice.add(titlePrice);
                        exTitle.add(subCatName);
                    }
                }
                if (driver.findElements(xPaginationNext).size() > 0) {
                    driver.findElement(xPaginationNext).click();
                } else break;

            } while (driver.findElements(xPaginationNext).size() > 0);
        }
        driver.switchTo().window(windowHandle1);





        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy'_'HH.mm.ss");
        String nowDate = formatForDateNow.format(dateNow);
        String fileName = "parse/parse_" + title + "_" + nowDate + ".csv";
        String fileNameForExcel = "parse/parse_" + title + "_" + nowDate + ".xlsx";
        System.out.println(fileName);


//        Writer  to csv
//        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "Windows-1251"));
//        writer.write(fileText);
//        writer.close();


//        Writer  to excel - format: xlsx
        File file = new File(fileNameForExcel);
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sh = wb.createSheet();


        for(int i=0; i<exName.size();i++){
            sh.createRow(i).createCell(0).setCellValue(exTitle.get(i));
            System.out.println("exTitle: " + exTitle.get(i));
            sh.getRow(i).createCell(1).setCellValue(exName.get(i));
            System.out.println("exName: " + exName.get(i));
            sh.getRow(i).createCell(2).setCellValue(exPrice.get(i).toString());
        }

        // We can set column width for each cell in the sheet
        sh.setColumnWidth(0, 7000);
        sh.setColumnWidth(1, 17700);
        sh.setColumnWidth(2, 4000);


        try {
            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        driver.close();
    }
}
