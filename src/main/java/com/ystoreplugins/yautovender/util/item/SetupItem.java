package com.ystoreplugins.yautovender.util.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class SetupItem {
	
	@SuppressWarnings("deprecation")
	public ItemStack setupMenuItem(ConfigurationSection section, boolean loreb) {
		Material material = Material.getMaterial(section.getInt("ID"));
		int data = section.getInt("Data");
		String url = section.getString("URL");
		boolean glow = section.getBoolean("Glow");
		boolean customskull = section.getBoolean("CustomSkull");
		String name = section.getString("Name").replace('&', '§');
		
		List<String> lore = new ArrayList<>();
		if (loreb) {
			section.getStringList("Lore").forEach(l -> lore.add(l.replace('&', '§')));
		}
		
		ItemStack topItem = null;
		
		if (customskull) {
			topItem = new CustomSkull().getSkull(url, name, lore, loreb);
			return topItem;
		}
		
		if (lore.size() == 0) {
			topItem = new ItemBuilder(material)
					.setDurability(data)
					.setName(name)
					.setGlow(glow)
					.toItemStack();
			return topItem;
		}
		
		topItem = new ItemBuilder(material)
				.setDurability(data)
				.setName(name)
				.setLore(lore)
				.setGlow(glow)
				.toItemStack();
		return topItem;
	}
}
