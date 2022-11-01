package lk.ijse.dep9.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class OrderDetailsDTO implements Serializable {

    private int qty;
    private BigDecimal unitPrice;
    private String itemCode;
    private String orderId;


    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int qty, BigDecimal unitPrice, String itemCode, String orderId) {
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.itemCode = itemCode;
        this.orderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", itemCode='" + itemCode + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
