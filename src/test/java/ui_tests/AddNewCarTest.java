package ui_tests;

import data_provider.CarDP;
import dto.Car;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetCarWorkPage;
import pages.LoginPage;
import utils.Fuel;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.*;
import static utils.RandomUtils.*;

@Listeners(TestNGListener.class)

public class AddNewCarTest extends ApplicationManager {
    LoginPage loginPage;
    LetCarWorkPage letCarWorkPage;

    @BeforeMethod
    public void login(){
        new HomePage(getDriver());
        loginPage= clickButtonsOnHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm("bilbo_baggins_12345@mail.com", "Password123!");
        letCarWorkPage= clickButtonsOnHeader(HeaderMenuItem.LET_CAR_WORK);

    }
    @Test
    public void addNewCarPositive(){
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.HYBRID.getValue())
                .seats(4)
                .carClass("C")
                .serialNumber("Opel->"+ generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .image("Lexus.jpg")
                .build();
        letCarWorkPage.typeaddNewCarForm(car);

    }

    @Test(dataProvider = "addNewCarDP", dataProviderClass = CarDP.class)
    public void addNewCarPositiveDP(Car car){
        letCarWorkPage.typeaddNewCarForm(car);

    }

    @Test
    public void addNewCarNegativeEmptyManufacture(){
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("")
                .model("Astra")
                .year("2020")
                .fuel("Gas")
                .seats(4)
                .carClass("C")
                .serialNumber("Opel->"+ generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .build();
        letCarWorkPage.typeaddNewCarForm(car);
        Assert.assertTrue(letCarWorkPage.validateMessageMakeIsRequired(), " Make is required ");

    }

    @Test
    public void addNewCarNegativeEmptyModel(){
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("")
                .year("2020")
                .fuel("Gas")
                .seats(4)
                .carClass("C")
                .serialNumber("Opel->"+ generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .build();
        letCarWorkPage.typeaddNewCarForm(car);
        Assert.assertTrue(letCarWorkPage.validateMessageModelIsRequired(), " Model is required ");

    }

    @Test
    public void addNewCarNegativeEmptyYear(){
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("")
                .fuel("Gas")
                .seats(4)
                .carClass("C")
                .serialNumber("Opel->"+ generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .build();
        letCarWorkPage.typeaddNewCarForm(car);
        Assert.assertTrue(letCarWorkPage.validateMessageYearIsRequired(), " Year is required ");

    }
    @Test
    public void addNewCarNegativeEmptyNumberOfSeats(){
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel("Gas")
                .seats(null)
                .carClass("C")
                .serialNumber("Opel->"+ generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .build();
        letCarWorkPage.typeaddNewCarForm(car);
        Assert.assertTrue(letCarWorkPage.validateMessageNumberOfSeatsIsRequired(), " Number of seats is required ");

    }
    @Test(dataProvider = "addNewCarDPFile", dataProviderClass = CarDP.class)
    public void addNewCarNegativeTestLesson(Car car){
        logger.info("Test data->" + car);
        letCarWorkPage.typeaddNewCarForm(car);
        Assert.assertFalse(letCarWorkPage.isEnabledSubmitBtn());

    }

}
