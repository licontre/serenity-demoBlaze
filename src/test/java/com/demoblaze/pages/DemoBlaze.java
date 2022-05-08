package com.demoblaze.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@DefaultUrl("https://www.demoblaze.com/index.html")
public class DemoBlaze  extends PageObject {

    private final static String phonesXpathButton= "//a[text()='Phones']";
    @FindBy(xpath = phonesXpathButton)
    private WebElement phonesButton;
    private final static String laptopXpathButton= "//a[text()='Laptops']";
    @FindBy(xpath = laptopXpathButton)
    private WebElementFacade laptopButton;
    private final static String monitorsXpathButton= "//a[text()='Monitors']";
    @FindBy(xpath = monitorsXpathButton)
    private WebElement monitorButton;

    public void goToLaptopSection(){
        waitFor(ExpectedConditions.elementToBeClickable(By.xpath(laptopXpathButton)));
        laptopButton.click();
    }
    public void goToPhoneSection(){
        waitFor(ExpectedConditions.elementToBeClickable(By.xpath(phonesXpathButton)));
        phonesButton.click();
    }
    public void goToMonitorsSection(){
        waitFor(ExpectedConditions.elementToBeClickable(By.xpath(monitorsXpathButton)));
        monitorButton.click();
    }
    public void gotToItemPage(String itemName){
        waitFor(ExpectedConditions.elementToBeClickable((By.xpath("//a[@class='hrefch']"))));
        List<WebElementFacade> itemsDisplayed = findAll(By.xpath("//a[@class='hrefch']"));
        boolean found = false;
        for(WebElementFacade product : itemsDisplayed){
            String name = product.getAttribute("innerText");
            if(name != null && name.equals(itemName)){
                product.click();
                found = true;
                break;
            }
        }
        if(!found){
            WebElementFacade nextPage = find(By.xpath("//button[@id='next2']"));
            nextPage.click();
            gotToItemPage(itemName);
        }
    }
    public  void addItemToCart(){
        waitFor(ExpectedConditions.elementToBeClickable((By.xpath("//a[@class='btn btn-success btn-lg']"))));
        WebElementFacade addToCartButton = find(By.xpath("//a[@class='btn btn-success btn-lg']"));
        WebElementFacade goHomeButton = find(By.xpath("//*[@id='navbarExample']/ul/li[1]/a"));
        WebElementFacade priceItem = find(By.xpath("//h3[@class='price-container']"));

        addToCartButton.click();
        waitFor(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
        waitFor(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id='navbarExample']/ul/li[1]/a"))));
        goHomeButton.click();
    }
    public void goToCart(){
        waitFor(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id='navbarExample']/ul/li[4]/a"))));
        WebElementFacade goCartButton = find(By.xpath("//*[@id='navbarExample']/ul/li[4]/a"));
        goCartButton.click();
    }

    public String removeItemFromCart(String itemName){
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='success']")));
        List<WebElementFacade>listItems =findAll(By.xpath("//tr[@class='success']"));
        String priceRemoved = "";
        for(WebElementFacade item: listItems){
            WebElementFacade itemNameElement = item.find(By.xpath("(.//td)[2]"));
            WebElementFacade itemPriceElement = item.find(By.xpath("(.//td)[3]"));
            WebElementFacade itemDeleteElement = item.find(By.xpath("(.//td)[4]/a"));
            String itemNameElementValue =itemNameElement.getAttribute("innerText");
            String itemPriceElementValue =itemPriceElement.getAttribute("innerText").replace(" ","");
            if(itemNameElementValue!=null&&itemNameElementValue.equals(itemName)){
                priceRemoved = itemPriceElementValue;
                itemDeleteElement.click();
                break;
            }
        }
        return priceRemoved;
    }

    public void placeOrderOnCart(){
        waitFor(ExpectedConditions.elementToBeClickable((By.xpath("//button[@class='btn btn-success']"))));
        WebElementFacade placeOrderButton = find(By.xpath("//button[@class='btn btn-success']"));
        placeOrderButton.click();
    }

    public  void fillOrderData(){
        WebElementFacade nameInputElement = find(By.xpath("//input[@id='name']"));
        WebElementFacade countryInputElement = find(By.xpath("//input[@id='country']"));
        WebElementFacade cityInputElement = find(By.xpath("//input[@id='city']"));
        WebElementFacade cardInputElement = find(By.xpath("//input[@id='card']"));
        WebElementFacade monthInputElement = find(By.xpath("//input[@id='month']"));
        WebElementFacade yearInputElement = find(By.xpath("//input[@id='year']"));

        nameInputElement.type("Jose");
        countryInputElement.type("Spain");
        cityInputElement.type("Malaga");
        cardInputElement.type("378282246310005");
        monthInputElement.type("04");
        yearInputElement.type("2023");

    }
    public void clickOnConfirmOrder(){
        waitFor(ExpectedConditions.elementToBeClickable((By.xpath("//button[text()='Purchase']"))));
        WebElementFacade confirmButton = find(By.xpath("//button[text()='Purchase']"));
        confirmButton.click();
    }

    public String getOrderData(){
        waitFor(ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[@class='lead text-muted ']"))));
        WebElementFacade orderBoxElement = find(By.xpath("//p[@class='lead text-muted ']"));
        return orderBoxElement.getAttribute("innerText");
    }
    public void finishOrder(){
        waitFor(ExpectedConditions.elementToBeClickable((By.xpath("//button[@class='confirm btn btn-lg btn-primary']"))));
        WebElementFacade finishButton = find(By.xpath("//button[@class='confirm btn btn-lg btn-primary']"));
        finishButton.click();
    }
}
