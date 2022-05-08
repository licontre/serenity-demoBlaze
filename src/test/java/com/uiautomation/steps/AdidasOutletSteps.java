package com.uiautomation.steps;

import com.uiautomation.actions.AdidasOutletActions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class AdidasOutletSteps {
        private int numOfItems = 0;
        private int selectedSize = 41;
        @Steps
        AdidasOutletActions adidasOutletActions;

        @Given("the user is navigating to outlet Page")
        public void theUserIsNavigatingToOutletPage() throws Throwable {
                adidasOutletActions.openOutletPage();
        }

        @When("the user click on originals section")
        public void theUserClickOnOriginalsSection() throws Throwable {
//                adidasOutletActions.openOriginalsSection();
                Thread.sleep(1200);
                numOfItems = adidasOutletActions.getCounterOfItems();
                Assert.assertTrue(numOfItems > 0);
        }

        @Then("shoes should be displayed")
        public void shoesShouldBeDisplayed() throws Throwable {
                adidasOutletActions.setShoesInTheFilter();
                Thread.sleep(1200);
                int tmpCount = adidasOutletActions.getCounterOfItems();
                Assert.assertTrue(tmpCount<numOfItems );
                numOfItems = tmpCount;
        }

        @And("can filter by size (\\d+(?:\\.\\d+)?)")
        public void canFilterBySize(float size) throws Throwable{
            int computedSize = Math.round(size);
            this.selectedSize = computedSize;
            adidasOutletActions.setShoeSizeInTheFilter(computedSize);
                Thread.sleep(1200);
                int tmpCount = adidasOutletActions.getCounterOfItems();
                Assert.assertTrue(tmpCount<numOfItems );
                numOfItems = tmpCount;
        }

        @When("the user finds one valid shoe")
        public void theUserFindsOneValidShoe() throws Throwable {
            String linkForThePurchase = adidasOutletActions.getFirstProductAvailableAndClick();
            Assert.assertTrue(validLink(linkForThePurchase));
            //TO GO TO  NEW LINK
            Thread.sleep(4000);
            adidasOutletActions.selectMySizeForAnItem(selectedSize);
        }

        @And("clicks on add to cart button")
        public void clicksOnAddToCartButton() throws Throwable {
            Thread.sleep(4000);
            adidasOutletActions.addToCartItem();
        }

        @Then("the product is not properly added because of bot Protection")
        public void theProductIsNotProperlyAddedBecauseOfBotProtection() throws Throwable{
            Thread.sleep(3000);
            Assert.assertTrue(adidasOutletActions.isBotProtectionEnabled());
        }
        /**
         * Can be improved using a regex validator
         * */
        public  boolean validLink(String link){
                return (link.indexOf("https://www.adidas")>-1)&&
                        (link.indexOf(".es")>-1)||
                        (link.indexOf("http://www.adidas")>-1)&&
                                (link.indexOf(".es")>-1);
        }
}
