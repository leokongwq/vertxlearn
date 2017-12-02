package com.leokongwq.vertx.microservice.order;

import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/30 下午8:47
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class Bootstrap extends Launcher {

    public static void main(String[] args) {
        new Bootstrap().dispatch(args);
    }

    @Override
    public void beforeStartingVertx(VertxOptions options) {
        super.beforeStartingVertx(options);
    }

    @Override
    public void afterStartingVertx(Vertx vertx) {
        super.afterStartingVertx(vertx);
    }
}
