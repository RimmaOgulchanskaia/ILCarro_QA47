package ui_tests;

import dto.Car;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetCarWorkPage;
import pages.LoginPage;
import utils.HeaderMenuItem;

import static pages.BasePage.*;
import static utils.RandomUtils.*;

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
                .fuel("Gas")
                .seats(4)
                .carClass("C")
                .serialNumber("Opel->"+ generateString(7))
                .pricePerDay(100.77)
                .about("about").build();
        letCarWorkPage.typeaddNewCarForm(car);

    }

}
