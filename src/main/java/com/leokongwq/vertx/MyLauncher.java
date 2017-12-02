package com.leokongwq.vertx;

import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLClient;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/27 下午11:44
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class MyLauncher extends Launcher {

    public static void main(String[] args) {
        new MyLauncher().dispatch(args);
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
