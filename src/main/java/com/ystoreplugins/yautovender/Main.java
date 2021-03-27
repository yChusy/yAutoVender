package com.ystoreplugins.yautovender;

import com.ystoreplugins.yautovender.command.manager.LoadCommands;
import com.ystoreplugins.yautovender.dao.BlocoDao;
import com.ystoreplugins.yautovender.dao.BonusDao;
import com.ystoreplugins.yautovender.listener.BlockBreak;
import com.ystoreplugins.yautovender.method.SetupEconomy;
import com.ystoreplugins.yautovender.process.BlocoProcess;
import com.ystoreplugins.yautovender.process.BonusProcess;
import com.ystoreplugins.yautovender.util.Formatter;
import com.ystoreplugins.yscreenshare.util.ConfigUtils;
import lombok.Getter;
import lombok.Setter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Getter
    private static ConfigUtils bonus, blocos;

    @Getter @Setter
    private Economy econ;

    private static boolean disabled;

    @Override
    public void onEnable() {
        getLogger().info("Plugin carregado.");
        getLogger().info("Carregando sistemas. [0/2]");
        saveDefaultConfig();
        register();
    }

    @Override
    public void onDisable() {
        if (disabled) return;
        saveDefaultConfig();
        getLogger().info("Sistemas descarregados.");
        getLogger().info("Plugin descarregado.");
    }

    private void register(){
        if (!new SetupEconomy().setupEconomy(this)) {
            disabled = true;
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        reload(this);
        new LoadCommands(this);
        new BlockBreak(this);
    }

    public static void reload(Main main){
        bonus = new ConfigUtils(main, "bonus.yml");
        blocos = new ConfigUtils(main, "blocos.yml");
        BlocoDao.getBlocos().clear();
        BonusDao.getBonuses().clear();
        new BonusProcess(main);
        new BlocoProcess(main);
        Formatter.formats = (String[]) main.getConfig().getStringList("Formats").toArray(new String[0]);
    }
}
