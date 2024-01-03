package entity.shipping;

import jdk.jshell.spi.ExecutionControl;

public class Shipment {

    private int shipType;

    private String deliveryInstruction;

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public void setDeliveryInstruction(String deliveryInstruction) {
        this.deliveryInstruction = deliveryInstruction;
    }

    public String getShipmentDetail() {
        return shipmentDetail;
    }

    public void setShipmentDetail(String shipmentDetail) {
        this.shipmentDetail = shipmentDetail;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    private String shipmentDetail;

    private String deliveryTime;

    public Shipment(int shipType, String deliveryInstruction, String shipmentDetail, String deliveryTime) {
        super();
        if (shipType == utils.Configs.PLACE_RUSH_ORDER) {
            this.deliveryInstruction = deliveryInstruction;
            this.shipmentDetail = shipmentDetail;
            this.deliveryTime = deliveryTime;
        }
    }

    public Shipment(int shipType) {
        super();
        this.shipType =  shipType;
    }


    /**
     * @return String
     */
    //getter setter method
    public String getDeliveryInstruction() {
        return this.deliveryInstruction;
    }

}
