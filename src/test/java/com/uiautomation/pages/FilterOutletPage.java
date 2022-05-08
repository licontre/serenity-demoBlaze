package com.uiautomation.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@DefaultUrl("https://www.adidas.es/outlet")
public class FilterOutletPage extends PageObject {

	@FindBy(xpath = "//*[@id='main-content']/div/div[1]/div/div/div[3]/img")
	private  WebElement filterButton;
	@FindBy(xpath = "//*[@id='main-content']/div/div[1]/div/div/div[3]/div/div/div[14]/div/button")
	private WebElement applyFilter;
	@FindBy(xpath = "(//span[@title='Categoria de producto'])")
	private WebElement categoriesButton;
	@FindBy(xpath = "(//div[@class='options_container___3bQrh'])[3]/div/ul/li[2]/a")
	private WebElement shoesOption;
	@FindBy(xpath = "(//span[@data-auto-id='plp-sidebar-filter-name'])[3]")
	private WebElement sizeButton;


	public void setShoesInTheFilter() throws Exception{
//		waitForCondition().until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[@id='main-content']/div/div[2]/div/div/div[2]/div[5]/div/div"), "Filtrar"));
		categoriesButton.click();
		Thread.sleep(2000);
		shoesOption.click();
	}

	public  void setShoeSizeInTheFilter(String size)throws  Exception{
		sizeButton.click();
		Thread.sleep(1500);
		List<WebElementFacade> sizeOptions = findAll(By.xpath("//li[@class='gl-square-list__item']"));
		for(WebElementFacade elem : sizeOptions){
			String textValue = ((String) elem.getAttribute("innerText")).toUpperCase();
			if(textValue != "" && textValue.equals(size)){
				elem.click();
				break;
			}
		}
	}
}
