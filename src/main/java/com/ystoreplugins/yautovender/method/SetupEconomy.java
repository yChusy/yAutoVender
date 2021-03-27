package com.ystoreplugins.yautovender.method;

import com.ystoreplugins.yautovender.Main;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

public class SetupEconomy {
	
	public boolean setupEconomy(Main main) {
		RegisteredServiceProvider<Economy> rsp = main.getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			main.getLogger().severe("Plugin de economia não encontrado.");
			return false;
		}
		main.getLogger().info("Plugin (Vault e Economia) encontrados. Realizando Hook...");
		main.setEcon(rsp.getProvider());
		return true;
	}
}