package com.leokongwq.vertx;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/27 下午10:56
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public class MyCommandFactory extends DefaultCommandFactory<MyCommand> {

    public MyCommandFactory() {
        super(MyCommand.class);
    }
}
