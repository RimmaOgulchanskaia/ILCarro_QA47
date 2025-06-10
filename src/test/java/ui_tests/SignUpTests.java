package ui_tests;

import dto.userLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;

import static utils.RandomUtils.generateEmail;

public class SignUpTests extends ApplicationManager {

    HomePage homePage;
    SignUpPage signUpPage;

    @BeforeMethod
    public void goToLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnSignUpHeader();
        signUpPage = new SignUpPage(getDriver());
    }

    @Test
    public void SignUpPositiveTest() {
        signUpPage.typeSignUpForm("Rimma", "Ogulchanskaia", "rimma@gmail.com", "Rimma@12345!");


    }

    @Test
    public void SignUpPositiveTestLombok() {
        userLombok user = userLombok.builder()
                .username("Kate")
                .lastName("Gross")
                .email(generateEmail(10))
                .password("Gross12345!")
                .build();

        signUpPage.typeSignUpForm(user.getUsername(), user.getLastName(), user.getEmail(), user.getPassword());
        Assert.assertTrue(signUpPage.validatePopUpMessage("You are logged in success"));


    }

    @Test
    public void SignUpNegativeTestDuplicateLombok() {
        userLombok user = userLombok.builder()
                .username("Kate")
                .lastName("Gross")
                .email("grossil@gmail.com")
                .password("Gross12345!")
                .build();

        signUpPage.typeSignUpForm(user.getUsername(), user.getLastName(), user.getEmail(), user.getPassword());
        Assert.assertTrue(signUpPage.validatePopUpMessage("User already exists"));
    }

    @Test
    public void SignUpNegativeTestWrongPasswordWithoutUpperLetterLombok() {
        userLombok user = userLombok.builder()
                .username("Kate")
                .lastName("Gross")
                .email(generateEmail(10))
                .password("gross12345!")
                .build();

        signUpPage.typeSignUpForm(user.getUsername(), user.getLastName(), user.getEmail(), user.getPassword());
        Assert.assertTrue(signUpPage.validateMessageErrorInPassword(), "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
    }

}
