package com.leokongwq.vertx.microservice.common;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/30 下午5:37
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class RestApiVerticle extends BaseMicroserviceVerticle {

    protected Future<Void> createHttpServer(Router router, String ip, int port) {
        Future<HttpServer> createHttpServerFuture = Future.future();
        vertx.createHttpServer().requestHandler(router::accept).listen(port, createHttpServerFuture.completer());
        return createHttpServerFuture.map(r -> null);
    }

    protected Handler<AsyncResult<Void>> resultVoidHandler(RoutingContext context, int status) {
        return ar -> {
            if (ar.succeeded()) {
                context.response()
                    .setStatusCode(status == 0 ? 200 : status)
                    .putHeader("content-type", "application/json")
                    .end();
            } else {
                internalError(context, ar.cause());
                ar.cause().printStackTrace();
            }
        };
    }

    protected  <T> Handler<AsyncResult<T>> resultNotEmptyHandler(RoutingContext context) {
        return asyncResult -> {
            if (asyncResult.succeeded()) {
                if (null != asyncResult.result()) {
                    context.response().
                        putHeader("content-type", "application/json")
                        .end(asyncResult.result().toString());
                } else {
                    notFound(context);
                }
            } else {
                asyncResult.cause().printStackTrace();
                internalError(context, asyncResult.cause());
            }
        };
    }

    protected void notFound(RoutingContext context) {
        context.response().setStatusCode(404)
            .putHeader("content-type", "application/json")
            .end(new JsonObject().put("message", "not_found").encodePrettily());
    }

    private void internalError(RoutingContext context, Throwable ex) {
        context.response().setStatusCode(500)
            .putHeader("content-type", "application/json")
            .end(new JsonObject().put("error", ex.getMessage()).encodePrettily());
    }


}
