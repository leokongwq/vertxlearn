/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.leokongwq.vertx.microservice.order.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link com.leokongwq.vertx.microservice.order.model.Order}.
 *
 * NOTE: This class has been automatically generated from the {@link com.leokongwq.vertx.microservice.order.model.Order} original class using Vert.x codegen.
 */
public class OrderConverter {

  public static void fromJson(JsonObject json, Order obj) {
    if (json.getValue("buyerId") instanceof String) {
      obj.setBuyerId((String)json.getValue("buyerId"));
    }
    if (json.getValue("createTime") instanceof Number) {
      obj.setCreateTime(((Number)json.getValue("createTime")).intValue());
    }
    if (json.getValue("id") instanceof Number) {
      obj.setId(((Number)json.getValue("id")).longValue());
    }
    if (json.getValue("orderId") instanceof Number) {
      obj.setOrderId(((Number)json.getValue("orderId")).longValue());
    }
    if (json.getValue("payId") instanceof String) {
      obj.setPayId((String)json.getValue("payId"));
    }
    if (json.getValue("products") instanceof JsonArray) {
      java.util.ArrayList<com.leokongwq.vertx.microservice.product.model.Product> list = new java.util.ArrayList<>();
      json.getJsonArray("products").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new com.leokongwq.vertx.microservice.product.model.Product((JsonObject)item));
      });
      obj.setProducts(list);
    }
    if (json.getValue("totalPrice") instanceof Number) {
      obj.setTotalPrice(((Number)json.getValue("totalPrice")).intValue());
    }
  }

  public static void toJson(Order obj, JsonObject json) {
    if (obj.getBuyerId() != null) {
      json.put("buyerId", obj.getBuyerId());
    }
    if (obj.getCreateTime() != null) {
      json.put("createTime", obj.getCreateTime());
    }
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getOrderId() != null) {
      json.put("orderId", obj.getOrderId());
    }
    if (obj.getPayId() != null) {
      json.put("payId", obj.getPayId());
    }
    if (obj.getProducts() != null) {
      JsonArray array = new JsonArray();
      obj.getProducts().forEach(item -> array.add(item.toJson()));
      json.put("products", array);
    }
    if (obj.getTotalPrice() != null) {
      json.put("totalPrice", obj.getTotalPrice());
    }
  }
}