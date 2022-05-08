package com.uiautomation.pages;

import com.uiautomation.actions.AdidasItemActions;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

@DefaultUrl("https://www.adidas.es/summer_celebration")
public class AdidasOutletPage extends PageObject {
	private static final String PRODUCT_ELEMENT = "//div[@class='glass-product-card__assets']";
	@FindBy(xpath = "//*[@id='modal-root']/div/div/div/div[2]/div/div[2]/button[1]")
	private WebElement cookiesButton;
	@FindBy(xpath = "//a[@title='ORIGINALS']")
	private WebElement originalsButton;
	@FindBy(className = "gl-modal__close")
	private WebElement closeSpan;
	@FindBy(className = "count___1ZIhY")
	private WebElement counterItems;
	@FindBy(xpath = "//*[@id='main-content']/div/div[1]/div/div/div[3]/img")
	private  WebElement filterButton;
	@FindBy(xpath = "//*[@id='main-content']/div/div[1]/div/div/div[3]/div/div/div[14]/div/button")
	private WebElement applyFilter;
	@FindBy(xpath = "//*[@id='main-content']/div/div[2]/div/div/div[2]/div[5]/div/div/div[2]/div[5]/div[1]/span")
	private WebElement categoriesButton;

	private FilterOutletPage filterPage;
	private AdidasItemPage itemPage;

	@WhenPageOpens
	public void acceptCookies() {
		cookiesButton.click();
	}

	public void clickOnOriginalSection() throws Exception {
		originalsButton.click();
		waitForCondition().until(ExpectedConditions.textToBePresentInElement(By.id("main-content"), "ORIGINALS"));
	}

	public void closeDiscoutPopUp() throws  Exception{
		Thread.sleep(3000);
		if(closeSpan != null){
			closeSpan.click();
		}
	}
	public void setShoesInTheFilter() throws Exception{
		openFilter();
		Thread.sleep(3000);
		filterPage.setShoesInTheFilter();
		applyFilter();
	}

	public void setShoeSizeInTheFilter(int size) throws Exception{
		openFilter();
		Thread.sleep(3000);
		filterPage.setShoeSizeInTheFilter(Integer.toString(size));
		Thread.sleep(1000);
		applyFilter();
		Thread.sleep(3000);
	}

	private void openFilter() throws Exception{
		filterButton.click();
	}

	private void applyFilter() throws Exception{
		applyFilter.click();
	}

	public int getCounterOfItems(){
		WebElementFacade elem = find(By.xpath("//div[@class='count___1ZIhY']"));
		String counterValue = elem.getAttribute("innerText");
		counterValue = counterValue.substring(1,counterValue.length()-2);
		return Integer.parseInt(counterValue);
	}

	public WebElement getFirstProduct(){
		List<WebElementFacade> listElem = findAll(By.xpath(PRODUCT_ELEMENT));
		return listElem.get(0);
	}
	public String getLinkFromProduct(WebElement elem){
		WebElement aElement = elem.findElement(By.tagName("a"));
		String link = aElement.getAttribute("href");
		System.out.println(link);
		return link;
	}
	public String getFirstProductLink(){
		WebElement el = getFirstProduct();
		String link = getLinkFromProduct(el);
		System.out.println(link);
		return  link;
	}

	public String getFirstProductLinkAndClick(){
		WebElement el = getFirstProduct();
		String link = getLinkFromProduct(el);
		System.out.println(link);
		el.click();
		return  link;
	}
	public boolean isBotProtectionEnabled(){
		return itemPage.isBotProtectionEnabled();
	}
	public void selectMySize(int size) throws  Throwable{
		itemPage.selectMySize(size);
	}
	public void addProductToCart() throws  Throwable{
		itemPage.addProductToCart();
	}
}
