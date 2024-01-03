import controller.PlaceOrderController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PlaceOrderControllerTest {


    @Test
    public void testValidatePhoneNumber() {
        PlaceOrderController placeOrderController = new PlaceOrderController();

        // Perform the test
        assertTrue(placeOrderController.validatePhoneNumber("0123456789"));
        assertFalse(placeOrderController.validatePhoneNumber("123")); // Invalid length
        assertFalse(placeOrderController.validatePhoneNumber("abcdefghij")); // Contains non-numeric characters
    }

   @Test
   public void testValidateContainLetterAndNoEmpty(){
       PlaceOrderController placeOrderController = new PlaceOrderController();
       // Perform the test
       assertFalse(placeOrderController.validateContainLetterAndNoEmpty(null));
       assertFalse(placeOrderController.validateContainLetterAndNoEmpty("    "));
       assertFalse(placeOrderController.validateContainLetterAndNoEmpty("abcdefghij123"));
       assertTrue(placeOrderController.validateContainLetterAndNoEmpty("Duc Hiep"));
       assertTrue(placeOrderController.validateContainLetterAndNoEmpty("Ngo giap bat"));
   }


    @Test
    public void testCalculateShippingFee() {
        PlaceOrderController placeOrderController = new PlaceOrderController();

        // Perform the test
        int shippingFee = placeOrderController.calculateShippingFee(100);
        assertTrue(shippingFee >= 0 && shippingFee <= 10);
    }

    @Test
    public void testValidateAddressPlaceRushOrder(){
        PlaceOrderController placeOrderController = new PlaceOrderController();
        assertFalse(placeOrderController.validateAddressPlaceRushOrder(" ", " "));
        assertFalse(placeOrderController.validateAddressPlaceRushOrder(" ", "Ngo giap bat"));
        assertTrue(placeOrderController.validateAddressPlaceRushOrder("Hà Nội", "Ngo giap bat"));
    }


}
