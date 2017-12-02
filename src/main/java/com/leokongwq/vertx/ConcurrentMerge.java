package com.leokongwq.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/17 下午6:07
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class ConcurrentMerge extends AbstractVerticle {

    @Override
    public void start(){
        //默认80端口
        HttpServer httpServer = vertx.createHttpServer();
        //默认 0 端口
        NetServer netServer = vertx.createNetServer();

        Future<HttpServer> httpServerFuture = Future.future();
        httpServer.listen(httpServerFuture.completer());

        Future<NetServer> netServerFuture = Future.future();
        netServer.listen(netServerFuture.completer());

        CompositeFuture.all(httpServerFuture, netServerFuture).setHandler(result -> {
            if (result.succeeded()) {
                // 所有服务器启动完成
            } else {
                // 有一个服务器启动失败
            }
        });
    }
}
