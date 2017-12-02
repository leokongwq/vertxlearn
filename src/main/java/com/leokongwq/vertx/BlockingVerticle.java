package com.leokongwq.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.WorkerExecutor;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/17 下午5:53
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class BlockingVerticle extends AbstractVerticle {

    int poolSize = 10;
    // 2分钟
    long maxExecuteTime = 120000;

    WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool", poolSize, maxExecuteTime);

//    @Override
//    public void start() throws Exception {
//        vertx.createHttpServer().requestHandler(req -> {
//            vertx.executeBlocking(future -> {
//                future.complete("hello kitty");
//            }, (AsyncResult<String> result) -> {
//                System.out.println(result.result());
//                req.response().end(result.result());
//            });
//        });
//    }

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            executor.executeBlocking(future -> {
                future.complete("hello kitty");
            }, (AsyncResult<String> result) -> {
                System.out.println(result.result());
                req.response().end(result.result());
            });
        });
    }
}
