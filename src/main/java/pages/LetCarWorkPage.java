package pages;

import dto.Car;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LetCarWorkPage extends BasePage{
    public LetCarWorkPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "pickUpPlace")
    WebElement inputCity;

    @FindBy(xpath = "//div[@class ='pac-container hdpi']//button")
    WebElement googlMapsBtnOk;

    @FindBy(id = "make")
    WebElement inputManufactura;

    @FindBy(id = "model")
    WebElement inputModel;

    @FindBy(id = "year")
    WebElement inputYear;

    @FindBy(id = "fuel")
    WebElement selectFuel;

    @FindBy(id = "seats")
    WebElement inputSeats;

    @FindBy(id = "class")
    WebElement inputCarClass;

    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;

    @FindBy(id = "price")
    WebElement inputPrice;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;


    public void typeaddNewCarForm(Car car) {
        inputCity.sendKeys(car.getCity());
        googlMapsBtnOk.click();
        inputManufactura.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        selectFuel.sendKeys(car.getFuel());
        inputSeats.sendKeys(car.getSeats().toString());
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(car.getPricePerDay() + "");

    }
}