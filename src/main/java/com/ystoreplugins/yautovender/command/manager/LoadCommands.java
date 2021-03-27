package com.ystoreplugins.yautovender.command.manager;

import com.ystoreplugins.yautovender.Main;
import com.ystoreplugins.yautovender.command.AutoVenderCommand;
import com.ystoreplugins.yautovender.engine.CommandEngine;

public class LoadCommands {

    public LoadCommands(Main main){
        new CommandEngine(main);
        CommandEngine.register(new AutoVenderCommand());
        main.getLogger().info("Comandos registrados. [2/2]");
    }
}
