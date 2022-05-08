package com.uiautomation.actions;

import com.uiautomation.pages.AdidasItemPage;
import com.uiautomation.pages.AdidasOutletPage;
import net.thucydides.core.annotations.Step;

public class AdidasItemActions {
	AdidasItemPage itemPage;

	@Step
	public void selectMySize(int size) throws  Exception{
		Thread.sleep(2000);
		itemPage.open();
		Thread.sleep(2000);
		itemPage.selectMySize(size);
	}
	@Step
	public void addToCart() throws  Exception{
		itemPage.addProductToCart();
		Thread.sleep(5000);
	}
	@Step
	public boolean isBotProtectionEnabled(){
		return itemPage.isBotProtectionEnabled();
	}
}