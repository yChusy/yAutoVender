package com.ystoreplugins.yautovender.engine;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.Locales;
import com.ystoreplugins.yautovender.Main;
import lombok.Getter;

public class CommandEngine {

    @Getter
    private static BukkitCommandManager manager = new BukkitCommandManager(Main.getPlugin(Main.class));

    public CommandEngine(Main main){
        main.getLogger().info("Língua definida para português. [1/2]");
        manager.getLocales().setDefaultLocale(Locales.PORTUGUESE);
    }

    public static void register(BaseCommand command){
        manager.registerCommand(command);
    }
}
