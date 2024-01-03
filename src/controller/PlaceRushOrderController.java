package controller;

import entity.shipping.Shipment;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * This class controls the flow of place rush order usecase in our AIMS project
 *
 */
public class PlaceRushOrderController extends BaseController {
    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());


    /**
     * @param deliveryData
     * @param typeDelivery
     */
    public static void validatePlaceRushOrderData(Shipment deliveryData) {
        if (deliveryData.getShipType() == utils.Configs.PLACE_RUSH_ORDER) {
           // validate
        }
    }
}
