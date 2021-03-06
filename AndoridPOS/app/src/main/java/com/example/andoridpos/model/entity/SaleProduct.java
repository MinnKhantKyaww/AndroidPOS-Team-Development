package com.example.andoridpos.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "sale_product",
        primaryKeys = {"product_id", "sale_time"},
        foreignKeys = {
        @ForeignKey(
                entity = Sale.class,
                parentColumns = "id",
                childColumns = "sale_id"),
        @ForeignKey(
                entity = Product.class,
                parentColumns = "id",
                childColumns = "product_id")
})
public class SaleProduct {

    @Embedded
    private SaleProductId id;
    private double price;
    private int quantity;
    private String name;

    @ColumnInfo(name = "sale_id")
    private long saleId;

    public SaleProduct() {
        id = new SaleProductId();
    }

    //Embedded Class
    public class SaleProductId {
        @ColumnInfo(name = "product_id")
        private int productId;
        @ColumnInfo(name = "sale_Time")
        private long saleTime;

        public SaleProductId() {

        }

        public SaleProductId(int productId, long saleTime) {
            this.productId = productId;
            this.saleTime = saleTime;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public long getSaleTime() {
            return saleTime;
        }

        public void setSaleTime(long saleTime) {
            this.saleTime = saleTime;
        }
    }

    public SaleProductId getId() {
        return id;
    }

    public void setId(SaleProductId id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleProduct)) return false;
        SaleProduct that = (SaleProduct) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 &&
                getQuantity() == that.getQuantity() &&
                getSaleId() == that.getSaleId() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice(), getQuantity(), getName(), getSaleId());
    }
}
