package com.demoblaze.steps;

import com.demoblaze.actions.DemoBlazeActions;
import com.demoblaze.pages.DemoBlaze;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import net.thucydides.core.annotations.Steps;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class BuyOneLaptopSteps {
    private String section;
    private int priceOrder;
    private String orderId;
    private Logger LOGGER = Logger.getLogger(BuyOneLaptopSteps.class.getName());
    @Steps
    private DemoBlazeActions demoBlazeActions;

    @Given("the user is on our DEMO ONLINE SHOP")
    public void theUserIsOnOurDemoOnlineShop() throws Throwable {
        demoBlazeActions.goToWebSite();
//        Thread.sleep(3200);
    }
    @When("the user navigate to \"([^\"]*)\"")
    public void  theUserNavigateTo(String section){
        this.section = section;
        gotToYourSection(section);
    }

    @And("^Add to the cart the \"([^\"]*)\"$")
    public void add_to_the_cart_the(String item) throws Exception {
        // Write code here that turns the phrase above into concrete action
//        demoBlazeActions.goToLaptops();
        gotToYourSection(section);
        demoBlazeActions.goToItemPage(item);
    }

    @Then("^I can see my two items on the cart$")
    public void i_can_see_my_two_items_on_the_cart() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        demoBlazeActions.goToCart();
    }

    @When("^deletes \"([^\"]*)\" from cart$")
    public void deletes_Dell_i_gb_from_cart(String item) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        demoBlazeActions.removeItemFromCart(item);

    }

    @When("^clicks on place Order$")
    public void clicks_on_place_Order() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        demoBlazeActions.placeOrder();
    }

    @And("^fills all the fields$")
    public void fills_all_the_fields() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        demoBlazeActions.fillOrderData();
    }

    @And("^click on Purchase$")
    public void click_on_Purchase() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        demoBlazeActions.confirmOrder();
        String orderId = demoBlazeActions.getOrderId();
        String orderAmount = demoBlazeActions.getOrderAmount();
        this.orderId = orderId;
        this.priceOrder = new Integer(orderAmount);
    }
    private  void logMessage(String message){
        LOGGER.log(Level.WARNING, message);
    }

    @Then("the order price is (\\d+)")
    public void theOrderPriceIsAndFinishTheOrder(int price) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        demoBlazeActions.checkPrice(price,priceOrder);
        logMessage("OrderId: "+this.orderId+" -> "+this.priceOrder+" amount");
    }
    @And("finish the order")
    public void finishTheOrder() throws Exception{
        demoBlazeActions.finishOrder();
//        Thread.sleep(3000);
    }

    private void gotToYourSection(String section){
        switch (section){
            case "Laptops":
                demoBlazeActions.goToLaptops();
                break;
            case "Phones":
                demoBlazeActions.goToPhones();
                break;
            case "Monitors":
                demoBlazeActions.goToMonitors();
                break;
            default:
                demoBlazeActions.goToLaptops();
        }
    }

}
