package com.leokongwq.vertx.microservice.order.impl;

import com.leokongwq.vertx.microservice.common.service.JdbcRepositoryWrapper;
import com.leokongwq.vertx.microservice.order.OrderService;
import com.leokongwq.vertx.microservice.order.model.Order;

import java.util.List;
import java.util.stream.Collectors;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/30 下午5:00
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class OrderServiceImpl extends JdbcRepositoryWrapper implements OrderService {

    private Vertx vertx;

    private JsonObject jdbcConfig;

    public OrderServiceImpl(Vertx vertx, JsonObject jdbcConfig) {
        super(vertx, jdbcConfig);
    }

    @Override
    public OrderService addOrder(Order order, Handler<AsyncResult<Long>> handler) {
        JsonArray params = new JsonArray()
            .add(order.getOrderId())
            .add(order.getPayId())
            .add(order.getBuyerId())
            .add(order.getCreateTime())
            .add(new JsonArray(order.getProducts()).encode())
            .add(order.getTotalPrice());
        super.insert(INSERT_SQL, params).setHandler(handler);
        return this;
    }

    @Override
    public OrderService deleteOrderByCode(Long orderId, Handler<AsyncResult<Integer>> handler) {
        super.update("delete from blue_order where order_id = ?", new JsonArray().add(orderId)).setHandler(handler);
        return this;
    }

    @Override
    public OrderService updateOrder(Order order, Handler<AsyncResult<Integer>> handler) {
        JsonArray params = new JsonArray()
            .add(order.getOrderId())
            .add(order.getCreateTime())
            .add(new JsonArray(order.getProducts()).encode())
            .add(order.getTotalPrice());
        super.update("update blue_order set create_time=?, products=? where order_id=?", params).setHandler(handler);
        return this;
    }

    @Override
    public OrderService getOrderById(Long orderId, Handler<AsyncResult<Order>> handler) {
        super.queryOne("select * from blue_order where id = ?", new JsonArray().add(orderId))
            .map(option -> option.map(Order::new).orElse(null)).setHandler(handler);
        return this;
    }

    @Override
    public OrderService getOrders(int offset, int pageSize, Handler<AsyncResult<List<Order>>> handler) {
        super.queryList("select * from blue_order order by id limit ?, ?", new JsonArray().add(offset).add(pageSize))
            .map(
                orders -> orders.stream().map(Order::new).collect(Collectors.toList())
            ).setHandler(handler);
        return this;
    }


    private static final String INSERT_SQL = "insert into blue_order (orderId, payId, buyerId, createTime, products, totalPrice) "
                                       + "values (?, ?, ?, ?, ?, ?)";
}
