package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10),this);

    }

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnLoginHeader;

    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUpHeader;
    //одно и тоже с записью WebElement btn...= driver.findElement(By.xpath...

    @FindBy(id= "city")
    WebElement inputCity;

    @FindBy(id= "dates")
    WebElement inputDates;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearMonthDay;




    public void typeSearchFormCalendar(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeYearMonthDay(startDate);
        typeYearMonthDay(endDate);
        removeDisabledBtnSearch();
        btnYalla.click();
    }

    private void typeYearMonthDay(LocalDate date) {
        btnYearMonthDay.click();
        String year = Integer.toString(date.getYear());  //2025
        driver.findElement(By.xpath("//div[contains(text(),'" + year + "')]")).click();//div[contains(text(),'2025')]
        String month = date.getMonth().toString();
        System.out.println(month.substring(0,3));
        driver.findElement(By.xpath("//div[contains(text(),'" + month.substring(0,3) + "')]")).click();
        String day = String.valueOf(date.getDayOfMonth());
        driver.findElement(By.xpath("//div[contains(text(),'" + day + "')]")).click();
    }


    public void typeSearchForm(String city, LocalDate startDate, LocalDate endDate){
        inputCity.sendKeys(city);
        inputDates.sendKeys(dateToString(startDate)+"-" + dateToString(endDate));
        removeDisabledBtnSearch();
        btnYalla.click();

    }

    private String dateToString(LocalDate date){
        return (date.getMonthValue()) + "/"+ date.getDayOfMonth()+"/" + date.getYear();

    }

    public void clickBtnLoginHeader(){
        btnLoginHeader.click();
    }

    public void clickBtnSignUpHeader(){

        btnSignUpHeader.click();
    }

}
