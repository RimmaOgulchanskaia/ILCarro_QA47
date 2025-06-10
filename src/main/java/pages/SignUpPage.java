package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SignUpPage extends BasePage {
    public SignUpPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10),this);
    }

    @FindBy(id="name")
    WebElement inputName;

    @FindBy(id="lastName")
    WebElement inputLastName;

    @FindBy(id="email")
    WebElement inputEmail;

    @FindBy(id="password")
    WebElement inputPassword;

    @FindBy(id = "terms-of-use")
    WebElement checkboxTerms;

    @FindBy(xpath = "//button[normalize-space()='Y’alla!']")
    WebElement btnYalla;

    @FindBy(xpath = "//div[@class='dialog-container']")
    WebElement popUpMessage;

    @FindBy(xpath = "//div[text()=' Password is required ']")
    WebElement messageErrorPassword;

    @FindBy(xpath = "//div[text()='Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]']")
    WebElement messageErrorInPassword;





    //public boolean validatePopUpMessage(String text) {
    //    return isTextElementPresent(popUpMessage, text);
    //}

    public boolean validatePopUpMessage(String text) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String popupText = popUpMessage.getText();
        System.out.println("Popup message: " + popupText);  // вот здесь выводим в консоль

        return popupText.contains(text);
    }

    //public boolean validateMessageErrorPassword(){
    //    return isElementPresent(messageErrorPassword);
    //}

    public boolean validateMessageErrorInPassword(){
        return isElementPresent(messageErrorInPassword);
    }

    public void  typeSignUpForm(String name, String lastname, String email, String password){
        inputName.sendKeys(name);
        inputLastName.sendKeys(lastname);
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxTerms);
        btnYalla.click();

    }





}
