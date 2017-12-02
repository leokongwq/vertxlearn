package com.leokongwq.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/11 下午7:14
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        final Router router = Router.router(vertx);

        router.get("/hello").handler(context -> {
            Integer.parseInt(context.request().getParam("age"));
            context.response().end("hello vert.x");
        }).failureHandler(context -> {
            context.response().end("Route internal error process");
        });
        router.get("/world").handler(context -> {
            Integer.parseInt(context.request().getParam("age"));
            context.response().end("hello vert.x");
        });
        router.route().last().handler(context -> {
            context.response().end("404");
        }).failureHandler(context -> {
           context.response().end("global error process");
        });
        vertx.createHttpServer().requestHandler(router::accept).listen(9090);
    }
}
