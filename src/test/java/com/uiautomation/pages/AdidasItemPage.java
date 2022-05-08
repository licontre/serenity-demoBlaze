package com.uiautomation.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdidasItemPage extends PageObject {
	@FindBy(xpath = "//button[@data-auto-id='add-to-bag']")
	private WebElement addToCart;

	public void selectMySize(int size) throws Exception{
		List <WebElementFacade> listElement = findAll(By.xpath("//button[@class='gl-label']"));
		for(WebElement elem :listElement){
			String value = elem.getAttribute("innerText");
			if(value!=""&&value!=null&&value.indexOf(Integer.toString(size))>-1){
				elem.click();
				break;
			}
		}
	}

	public  void addProductToCart() throws Exception{
		Thread.sleep(1500);
		addToCart.click();
	}

	public  boolean isBotProtectionEnabled(){
		WebElementFacade elem = find(By.xpath("//div[@data-auto-id='cart-error-message']"));
		return elem != null;
	}

}
