package com.uiautomation.actions;

import com.uiautomation.pages.AdidasOutletPage;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class AdidasOutletActions {
	AdidasOutletPage outletPage;

	@Step
	public void openOutletPage() {
		outletPage.open();
	}
	@Step
	public void openOriginalsSection() throws  Throwable{
		outletPage.clickOnOriginalSection();
		outletPage.closeDiscoutPopUp();
	}
	@Step
	public  void setShoesInTheFilter() throws Exception {
		outletPage.setShoesInTheFilter();
	}
	@Step
	public  void setShoeSizeInTheFilter(int size) throws Exception {
		outletPage.setShoeSizeInTheFilter(size);
		Thread.sleep(2000);
	}
	@Step
	public int getCounterOfItems() throws Exception{
		return outletPage.getCounterOfItems();
	}
	@Step
	public String getFirstProductAvailable(){
		String linkToProduct = "";
		linkToProduct = outletPage.getFirstProductLink();
		return linkToProduct;
	}
	@Step
	public String getFirstProductAvailableAndClick(){
		String linkToProduct = "";
		linkToProduct = outletPage.getFirstProductLinkAndClick();
		return linkToProduct;
	}
	@Step
	public void selectMySizeForAnItem(int size) throws Throwable{
		outletPage.selectMySize(size);
//		outletPage.itemPage.selectMySize(size);
	}
	@Step
	public void addToCartItem() throws Throwable{
		outletPage.addProductToCart();
//		outletPage.itemPage.addProductToCart();
	}
	@Step public  boolean isBotProtectionEnabled() throws  Exception{
		return outletPage.isBotProtectionEnabled();
//		 return outletPage.itemPage.isBotProtectionEnabled();
	}
}