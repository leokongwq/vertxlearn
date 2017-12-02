package com.leokongwq.vertx.microservice.product.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author : kongwenqiang
 * DateTime: 2017/12/1 下午4:36
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
@EqualsAndHashCode
@NoArgsConstructor
@DataObject(generateConverter = true)
public class Product {
    private Long productId;
    private String productCode;
    private Long sellerId;
    private Integer price;
    private Integer amount;

    public Product(Product other) {
        this.productId = other.productId;
        this.productCode = other.productCode;
        this.sellerId = other.sellerId;
        this.price = other.price;
        this.amount = other.amount;
    }

    public Product(JsonObject jsonObject) {
        ProductConverter.fromJson(jsonObject,this);
    }

    public Product(String json) {
        ProductConverter.fromJson(new JsonObject(json), this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ProductConverter.toJson(this, json);
        return json;
    }

    @Override
    public String toString() {
        return this.toJson().encodePrettily();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
