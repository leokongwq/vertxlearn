package com.leokongwq.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/17 下午6:14
 * Mail:kongwenqiang@qiyi.com
 * Description: desc
 */
public class SequenceMerge extends AbstractVerticle {

    @Override
    public void start() {
        FileSystem fs = vertx.fileSystem();
        Future<Void> startFuture = Future.future();

        Future<Void> fut1 = Future.future();
        fs.createFile("/data/foo", fut1.completer());

        fut1.compose(v -> {
                // fut1中文件创建完成后执行
                Future<Void> fut2 = Future.future();
                fs.writeFile("/data/foo", Buffer.buffer(), fut2.completer());
                return fut2;
            }
        ).compose(v -> {
            // fut2文件写入完成后执行
            fs.move("/foo", "/bar", startFuture.completer());
        },
        // 如果任何一步失败，将startFuture标记成failed
        startFuture);

        if (fut1.succeeded()) {
            fut1.result();
        } else {
            System.out.println(fut1.cause());
        }
    }
}
