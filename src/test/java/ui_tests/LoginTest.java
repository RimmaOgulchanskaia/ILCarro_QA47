package ui_tests;

import dto.userLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import static utils.RandomUtils.*;

public class LoginTest extends ApplicationManager {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        loginPage.typeLoginForb("bilbo_baggins_12345@mail.com", "Password123!");

    }

    @Test
    public void loginPositiveTestLombok() {
        userLombok user = userLombok.builder()
                .username("bilbo_baggins_12345@mail.com")
                .password("Password123!")
                .build();
        loginPage.typeLoginForb(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validatePopUpMessage("Logged in success"));
    }
    @Test
    public void loginNegativeTestUnRegUserLombok() {
        userLombok user = userLombok.builder()
                .username(generateEmail(10))
                .password("Password123!")
                .build();

        loginPage.typeLoginForb(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validatePopUpMessage("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTestEmptyPasswordLombok() {
        userLombok user = userLombok.builder()
                .username("bilbo_baggins_12345@mail.com")
                .password("")
                .build();

        loginPage.typeLoginForb(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validateMessageErrorPassword(), "LoginNegativeTest");


    }


}