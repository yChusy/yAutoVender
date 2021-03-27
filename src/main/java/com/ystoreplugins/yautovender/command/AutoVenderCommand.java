package com.ystoreplugins.yautovender.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.ystoreplugins.yautovender.Main;
import org.bukkit.command.CommandSender;

@CommandAlias("autovender|autosell")
@CommandPermission("yautovender.staff")
public class AutoVenderCommand extends BaseCommand {

    @Default
    public void onAutoVender(CommandSender sender) {
        Main.reload(Main.getPlugin(Main.class));
        sender.sendMessage("§aConfiguração recarregada.");
    }

}