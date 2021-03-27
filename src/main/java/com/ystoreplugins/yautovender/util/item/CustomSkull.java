package com.ystoreplugins.yautovender.util.item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class CustomSkull {
	
	public ItemStack getSkull(String url, String name, List<String> lore, boolean loreb) {
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta headMeta = (SkullMeta) head.getItemMeta();
		
		if (loreb) {
			List<String> nLore = new ArrayList<String>();
			
			lore.forEach(l -> nLore.add(l.replace('&', '§')));
			
			headMeta.setLore(nLore);
		}
		
		headMeta.setDisplayName(name);
		
		if (!url.isEmpty() && !url.equalsIgnoreCase("{player}")) {
			GameProfile profile = new GameProfile(UUID.randomUUID(), null);
			String format = String.format("{textures:{SKIN:{url:\"%s\"}}}", url);
			String encodedData = Base64.getEncoder().encodeToString(format.getBytes());
			profile.getProperties().put("textures", new Property("textures", encodedData));
			Field profileField = null;
			
			try {
				profileField = headMeta.getClass().getDeclaredField("profile");
				profileField.setAccessible(true);
				profileField.set(headMeta, profile);
			} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
		
		head.setItemMeta(headMeta);
		
		if (url.equalsIgnoreCase("{player}"))
			NBTTag.setNBTString(head.getItemMeta(), "yStore-PlayerSkull", "yes");
		
		return head;
	}
}
