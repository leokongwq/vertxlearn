package com.leokongwq.vertx;

import io.vertx.core.AbstractVerticle;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/11 下午7:23
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {
        vertx.deployVerticle(MyFirstVerticle.class.getName());
    }
}
