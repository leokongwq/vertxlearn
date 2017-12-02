package com.leokongwq.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/11 下午7:13
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class Main {

    public static void main(String[] args) {
        //VertxOptions 创建Vertx对象的选项对象。
        Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(5).setWorkerPoolSize(5));

//        vertx.deployVerticle(MyFirstVerticle.class.getName());
        vertx.deployVerticle(MyVerticle.class.getName());
    }
}
