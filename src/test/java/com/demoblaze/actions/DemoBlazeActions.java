package com.demoblaze.actions;

import com.demoblaze.pages.DemoBlaze;
import cucumber.runtime.java.StepDefAnnotation;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.Locale;

public class DemoBlazeActions {
    private DemoBlaze demoBlazePage;
    @Step
    public void goToWebSite(){
        demoBlazePage.open();
    }
    @Step
    public void goToLaptops(){
        demoBlazePage.goToLaptopSection();
    }

    @Step
    public void goToPhones(){
        demoBlazePage.goToPhoneSection();
    }
    @Step
    public  void goToMonitors(){
        demoBlazePage.goToMonitorsSection();
    }
    @Step
    public void  goToItemPage(String name){
        demoBlazePage.gotToItemPage(name);
        demoBlazePage.addItemToCart();
    }

    @Step
    public void goToCart(){
        demoBlazePage.goToCart();
    }
    @Step
    public void removeItemFromCart(String itemName){
        String price  = demoBlazePage.removeItemFromCart(itemName);
    }
    @Step
    public  void  placeOrder(){
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        demoBlazePage.placeOrderOnCart();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
    @Step
    public  void  fillOrderData(){
        demoBlazePage.fillOrderData();
    }
    @Step
    public  void  confirmOrder(){
        demoBlazePage.clickOnConfirmOrder();
    }
    @Step
    public String getOrderData(){
        return demoBlazePage.getOrderData();
    }
    @Step
    public String getOrderId(){
        String orderData = getOrderData();
        String[] lines = orderData.split("\n");
        String orderId = "";
        for (String line : lines){
            if(line.toLowerCase().indexOf("id")>-1){
                int posDelimiter = line.indexOf(":");
                orderId = line.substring(posDelimiter+1,line.length()).replace(" ","");
                break;
            }
        }
        return  orderId;
    }
    @Step
    public String getOrderAmount(){
        String orderData = getOrderData();
        String[] lines = orderData.split("\n");
        String amount = "";
        for (String line : lines){
            if(line.toLowerCase().indexOf("amount")>-1){
                int posDelimiter = line.indexOf(":");
                int posCurrency = line.indexOf("USD");
                amount = line.substring(posDelimiter+1,posCurrency).replace(" ","");
                break;
            }
        }
        return  amount;
    }
    @Step
    public void checkPrice(int price,int expectedPrice){
        Assert.assertEquals(price,expectedPrice);
    }
    @Step
    public  void finishOrder(){
        demoBlazePage.finishOrder();
    }
}
