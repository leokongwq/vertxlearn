package com.leokongwq.vertx;

import java.util.List;

import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.spi.launcher.DefaultCommand;

/**
 * @author : kongwenqiang
 * DateTime: 2017/11/27 下午10:56
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
@Name("hello")
@Summary("A simple hello command.")
public class MyCommand extends DefaultCommand {

    private String name;

    private List<String> hobbies;

    @Option(longName = "name", shortName = "n", required = true)
    public void setName(String n){
        this.name = n;
    }

    @Option(longName = "hobby", shortName = "h")
    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public void run() throws CLIException {
        System.out.println("hello " + name);
        System.out.println(this.hobbies);
    }
}
