package com.ystoreplugins.yautovender.process;

import com.ystoreplugins.yautovender.Main;
import com.ystoreplugins.yautovender.dao.BonusDao;
import com.ystoreplugins.yautovender.model.Bonus;
import org.bukkit.configuration.ConfigurationSection;

public class BonusProcess {
	
	public BonusProcess(Main main) {
		try {
			
			ConfigurationSection bonus = Main.getBonus().getConfig().getConfigurationSection("Bonus");
			
			for (String s : bonus.getKeys(false)) {
				ConfigurationSection dSec = bonus.getConfigurationSection(s);

				String permissao = dSec.getString("Permissao");
				String display = dSec.getString("Display").replace('&', '§');
				double bonusD = dSec.getDouble("Bonus");

				BonusDao.add(new Bonus(s, permissao, display, bonusD));
			}
			
			main.getLogger().info(BonusDao.getBonuses().size() + " bonus carregado(s): " + BonusDao.getBonuses().keySet());
			
		}catch (NullPointerException e) {
			main.getLogger().severe("Há um erro na bonus.yml");
		}
	}
}