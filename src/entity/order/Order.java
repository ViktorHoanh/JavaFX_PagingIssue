package entity.order;

import entity.db.AIMSDB;
import entity.media.Media;
import entity.shipping.Shipment;
import utils.Configs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {


    private int shippingFees;
    private List<OrderMedia> lstOrderMedia;
    private Shipment shipment;
    private String name;
    private String province;
    private String instruction;
    private String address;
    private String phone;

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<OrderMedia> getLstOrderMedia() {
        return lstOrderMedia;
    }

    public void setLstOrderMedia(List<OrderMedia> lstOrderMedia) {
        this.lstOrderMedia = lstOrderMedia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    public Order() {
        this.lstOrderMedia = new ArrayList<OrderMedia>();
    }

    public Order(List lstOrderMedia) {
        this.lstOrderMedia = lstOrderMedia;
    }

    public void createOrderEntity(){
        try {
            Statement stm = AIMSDB.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "INSERT INTO 'Order' (name, province, address, phone, shipping_fee) " +
                "VALUES ( ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = AIMSDB.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, province);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, shippingFees);


            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);

                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
            try (PreparedStatement preparedStatement2 = AIMSDB.getConnection().prepareStatement(query)) {
                var sqlinsertShipment = "INSERT INTO Shipping ( shipType, deliveryInstruction, dateTime, deliverySub, orderId) " +
                        "VALUES ( ?, ?, ?, ?)";
                preparedStatement2.setInt(1, shipment.getShipType());
                preparedStatement2.setString(2, shipment.getDeliveryInstruction());
                preparedStatement2.setString(3, shipment.getDeliveryTime());
                preparedStatement2.setString(4, shipment.getShipmentDetail());
                preparedStatement2.setInt(5, id);
              preparedStatement2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param om
     */
    public void addOrderMedia(OrderMedia om) {
        this.lstOrderMedia.add(om);
    }


    /**
     * @param om
     */
    public void removeOrderMedia(OrderMedia om) {
        this.lstOrderMedia.remove(om);
    }


    /**
     * @return List
     */
    public List<OrderMedia> getlstOrderMedia() {
        return this.lstOrderMedia;
    }


    /**
     * @param lstOrderMedia
     */
    public void setlstOrderMedia(List lstOrderMedia) {
        this.lstOrderMedia = lstOrderMedia;
    }

    /**
     * @return int
     */
    public int getShippingFees() {
        return shippingFees;
    }

    /**
     * @param shippingFees
     */
    public void setShippingFees(int shippingFees) {
        this.shippingFees = shippingFees;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    /**
     * @return int
     */
    public int getAmount() {
        double amount = 0;
        for (Object object : lstOrderMedia) {
            OrderMedia om = (OrderMedia) object;
            amount += om.getPrice();
        }
        return (int) (amount + (Configs.PERCENT_VAT / 100) * amount);
    }

}
