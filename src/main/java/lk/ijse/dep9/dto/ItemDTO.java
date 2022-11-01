package lk.ijse.dep9.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemDTO implements Serializable {
    private int stock;
    private String description;
    private BigDecimal unitPrice;

    public ItemDTO(String description) {
        this.description = description;
    }

    private String code;

    @Override
    public String toString() {
        return "ItemDTO{" +
                "stock=" + stock +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", code='" + code + '\'' +
                '}';
    }

    public ItemDTO() {
    }


    public ItemDTO(int stock, String description, BigDecimal unitPrice, String code) {
        this.stock = stock;
        this.description = description;
        this.unitPrice = unitPrice;
        this.code = code;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
