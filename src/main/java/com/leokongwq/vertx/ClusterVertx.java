package com.leokongwq.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/17 下午5:14
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class ClusterVertx {

    public static void main(String[] args) {

        VertxOptions vertxOptions = new VertxOptions();

        Vertx.clusteredVertx(vertxOptions, res -> {
            if (res.succeeded()) {
                // 获取到了集群模式下的 Vertx 对象
                Vertx vertx = res.result();
                // 做一些其他的事情
                vertx.setPeriodic(1000, id -> {
                    // This handler will get called every second
                    // 这个处理器将会每隔一秒被调用一次
                    System.out.println("timer fired!");
                });
            } else {
                // 获取失败，可能是集群管理器出现了问题
            }
        });
    }
}
