package com.leokongwq.vertx.microservice.order;

import com.leokongwq.vertx.microservice.order.api.OrderServiceRestApiVerticle;
import com.leokongwq.vertx.microservice.order.impl.OrderServiceImpl;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/30 下午4:57
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class OrderVerticle extends AbstractVerticle {

    private OrderService orderService;

    @Override
    public void start() throws Exception {
        super.start();
        orderService = new OrderServiceImpl(vertx, config());
        deployRestVerticle();
    }

    private Future<Void> deployRestVerticle() {
        Future<String> future = Future.future();
        vertx.deployVerticle(new OrderServiceRestApiVerticle(orderService),
                             new DeploymentOptions().setConfig(config()),
                             future.completer());
        return future.map(r -> null);
    }
}
