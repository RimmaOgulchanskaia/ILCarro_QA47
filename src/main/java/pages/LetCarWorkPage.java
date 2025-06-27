package pages;

import dto.Car;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Fuel;

import java.io.File;

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

    @FindBy(xpath = "//div[text()=' Make is required ']")
    WebElement messageMakeIsRequired;

    @FindBy(xpath = "//div[text()=' Model is required ']")
    WebElement messageModelIsRequired;

    @FindBy(xpath = "//div[text()=' Year required ']")
    WebElement messageYearIsRequired;

    @FindBy(xpath = "//div[text()=' Number of seats is required ']")
    WebElement messageNumberOfSeatsIsRequired;

    @FindBy(xpath = "//input[@type='file']")
    WebElement inputFile;

    public boolean validateMessageNumberOfSeatsIsRequired(){
        return isElementPresent(messageNumberOfSeatsIsRequired);
    }

    public boolean validateMessageYearIsRequired(){
        return isElementPresent(messageYearIsRequired);
    }

    public boolean validateMessageMakeIsRequired(){
        return isElementPresent(messageMakeIsRequired);
    }

    public boolean validateMessageModelIsRequired(){
        return isElementPresent(messageModelIsRequired);
    }


    public void typeaddNewCarForm(Car car) {
        inputCity.sendKeys(car.getCity());
        //googlMapsBtnOk.click();
        inputManufactura.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        //selectFuel.sendKeys(car.getFuel());
        //typeFuel(Fuel.DIESEL.getValue());
        typeFuel(car.getFuel());
        if (car.getSeats() == null) {
            inputSeats.click();
        } else {
            inputSeats.sendKeys(car.getSeats().toString());
        }
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(car.getPricePerDay() + "");
        //addPhoto(car.getImage());

    }

    private void addPhoto(String fileName) {
        inputFile.sendKeys(new File("src/test/resources/Photos/Lexus.jpg").getAbsolutePath());

    }

    private void typeFuel(String fuel) {
        if (fuel != null && !fuel.isBlank()) {
            Select select = new Select(selectFuel);
            select.selectByValue(fuel);
        } else {
            inputSeats.click();
        }
    }

    public boolean isEnabledSubmitBtn(){
        return elementIsEnabled(btnSubmit);
    }
}