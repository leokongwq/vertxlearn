package com.leokongwq.vertx.microservice.order.model;

import com.leokongwq.vertx.microservice.product.model.Product;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/30 下午4:58
 * Mail:kongwenqiang@qiyi.com   
 * Description: 拥有一个拷贝构造函数以及一个接受一个 JsonObject 对象的构造函数。
 */
@EqualsAndHashCode
@NoArgsConstructor
@DataObject(generateConverter = true)
public class Order {

    private Long id;
    private Long orderId;
    private String payId;
    private String buyerId;
    private List<Product> products;
    private Integer totalPrice;
    private Integer createTime;

    public Order(Order other) {
        this.id = other.id;
        this.orderId = other.orderId;
        this.buyerId = other.buyerId;
        this.payId = other.payId;
        this.products = other.products;
        this.totalPrice = other.totalPrice;
        this.createTime = other.createTime;
    }

    public Order(JsonObject json) {
        OrderConverter.fromJson(json, this);
        String products = json.getString("products");
        if (StringUtils.isNotBlank(products)) {
            this.products = new JsonArray(json.getString("products"))
                .stream()
                .map(e -> (JsonObject) e)
                .map(Product::new)
                .collect(Collectors.toList());
        }
    }

    public Order(String json) {
        OrderConverter.fromJson(new JsonObject(json), this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        OrderConverter.toJson(this, json);
        return json;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return this.toJson().encodePrettily();
    }
}
