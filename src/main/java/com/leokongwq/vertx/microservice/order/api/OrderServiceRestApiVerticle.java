package com.leokongwq.vertx.microservice.order.api;

import com.leokongwq.vertx.microservice.common.RestApiVerticle;
import com.leokongwq.vertx.microservice.common.web.RequestUtil;
import com.leokongwq.vertx.microservice.order.OrderService;
import com.leokongwq.vertx.microservice.order.model.Order;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/30 下午5:05
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class OrderServiceRestApiVerticle extends RestApiVerticle {

    private static final String API_ADD = "/orders";
    private static final String API_DELETE = "/orders/:id";
    private static final String API_UPDATE = "/orders/:id";
    private static final String API_RETRIEVE = "/orders/:id";
    private static final String API_RETRIEVE_ALL = "/orders";

    private OrderService orderService;

    public OrderServiceRestApiVerticle(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void start() throws Exception {
        super.start();
        final Router router = Router.router(vertx);
        // body handler
        router.route().handler(BodyHandler.create());
        // api route handler
        router.post(API_ADD).handler(this::apiAddOrder);
        router.delete(API_DELETE).handler(this::apiDeleteOrder);
        router.put(API_UPDATE).handler(this::apiUpdateOrder);
        router.get(API_RETRIEVE).handler(this::apiGetOrder);
        router.get(API_RETRIEVE_ALL).handler(this::apiGetOrders);

        router.route().handler(
            context -> context.response().end("404 Not Found !")
        ).failureHandler(context -> context.response().end("500 error occured!"));

        String host = config().getString("trade.order.http.address", "0.0.0.0");
        int port = config().getInteger("trade.order.http.port", 8081);

        // create HTTP server
        createHttpServer(router, host, port);
    }

    private void apiAddOrder(RoutingContext context) {
        orderService.addOrder(new Order(context.getBodyAsJson()), resultNotEmptyHandler(context));
    }

    private void apiDeleteOrder(RoutingContext context) {
        orderService.deleteOrderByCode(RequestUtil.getLong(context.request(), "id", 0L), resultNotEmptyHandler(context));
    }

    private void apiUpdateOrder(RoutingContext context){
        orderService.updateOrder(new Order(context.getBodyAsJson()), resultNotEmptyHandler(context));
    }

    private void apiGetOrder(RoutingContext context){
        Long orderId = Long.parseLong(context.request().getParam("id"));
        orderService.getOrderById(orderId, resultNotEmptyHandler(context));
    }

    private void apiGetOrders(RoutingContext context){
        int offset = RequestUtil.getInt(context.request(), "offset", 0);
        int pageSize = RequestUtil.getInt(context.request(), "pageSize", 10);
        orderService.getOrders(offset, pageSize, resultNotEmptyHandler(context));
    }
}
