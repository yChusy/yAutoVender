package com.ystoreplugins.yautovender.process;

import com.ystoreplugins.yautovender.Main;
import com.ystoreplugins.yautovender.dao.BlocoDao;
import com.ystoreplugins.yautovender.model.Bloco;
import org.bukkit.configuration.ConfigurationSection;

public class BlocoProcess {

	public BlocoProcess(Main main) {
		try {

			ConfigurationSection blocos = Main.getBlocos().getConfig().getConfigurationSection("Blocos");

			for (String s : blocos.getKeys(false)) {
				ConfigurationSection bSec = blocos.getConfigurationSection(s);
				String bloco = bSec.getInt("ID") + ":" + bSec.getInt("Data");
				String display = bSec.getString("Display").replace('&', '§');
				double money = bSec.getDouble("Money");
				double xp = bSec.getDouble("Xp");
				double mcmmo = bSec.getDouble("Mcmmo");
				boolean fortune = bSec.getBoolean("Fortune");

				Bloco b = new Bloco(s, bloco, display, money, xp, mcmmo, fortune);
				BlocoDao.add(b);
			}
			
			main.getLogger().info(BlocoDao.getBlocos().size() + " blocos carregado(s): " + BlocoDao.getBlocos().keySet());
			
		}catch (NullPointerException e) {
			main.getLogger().severe("Há um erro na blocos.yml");
		}
	}
}