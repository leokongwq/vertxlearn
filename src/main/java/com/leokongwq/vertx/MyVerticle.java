package com.leokongwq.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.WorkerExecutor;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/17 下午4:43
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class MyVerticle extends AbstractVerticle {

    //属性变量
    private int i;

    private WorkerExecutor executor;

    @Override
    public void start() throws Exception {
        executor = vertx.createSharedWorkerExecutor("my-worker-pool");
        vertx.createHttpServer().requestHandler(req->{
            //要关闭请求，否则连接很快会被占满
//            vertx.executeBlocking(future -> {
//                // 调用一些需要耗费显著执行时间返回结果的阻塞式API
//                //String result = someAPI.blockingMethod("hello");
//                future.complete("1");
//            }, false, res -> {
//                i++;
//                System.out.println("The result is: " + res.result());
//            });

            executor.executeBlocking(future -> {
                // 调用一些需要耗费显著执行时间返回结果的阻塞式API
                //String result = someAPI.blockingMethod("hello");
                future.complete("hello kitty");
            }, res -> {
                System.out.println("The result is: " + res.result());
            });

            req.response().end("i is " + i);
        }).listen(9090);

        vertx.createHttpServer().requestHandler(req->{
            System.out.println(i);
            req.response().end("" + i);
        }).listen(9091);
    }

}
