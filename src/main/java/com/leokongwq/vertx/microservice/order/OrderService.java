package com.leokongwq.vertx.microservice.order;

import com.leokongwq.vertx.microservice.order.model.Order;

import java.util.List;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/30 下午4:58
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public interface OrderService {

    /**
     * 添加订单
     * @param order 订单
     * @param handler 异步结果处理器
     * @return void
     */
    @Fluent
    OrderService addOrder(Order order, Handler<AsyncResult<Long>> handler);

    /**
     * 根据订单号删除订单
     * @param orderCode 订单号
     * @param handler 异步结果处理器
     * @return void
     */
    @Fluent
    OrderService deleteOrderByCode(Long orderCode, Handler<AsyncResult<Integer>> handler);

    /**
     * 修改订单
     * @param order 订单
     * @param handler 异步结果处理器
     * @return void
     */
    @Fluent
    OrderService updateOrder(Order order, Handler<AsyncResult<Integer>> handler);

    /**
     * 通过订单号查询订单号
     * @param orderId 订单号
     * @param handler 异步结果处理器
     * @return 订单
     */
    @Fluent
    OrderService getOrderById(Long orderId, Handler<AsyncResult<Order>> handler);

    @Fluent
    OrderService getOrders(int offset, int pageSize, Handler<AsyncResult<List<Order>>> handler);
}
