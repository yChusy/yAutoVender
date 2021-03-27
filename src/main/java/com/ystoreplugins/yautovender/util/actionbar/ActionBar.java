package com.ystoreplugins.yautovender.util.actionbar;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.ystoreplugins.yautovender.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ActionBar {

	public static void sendActionBar(Player player, String message) {
		Main main = Main.getPlugin(Main.class);
		message = ChatColor.translateAlternateColorCodes('&', message);

		String mcVersion = Bukkit.getServer().getClass().getPackage().getName();
		mcVersion = mcVersion.substring(mcVersion.lastIndexOf(".") + 1);

		try {
			if (mcVersion.startsWith("v1_16_")) {
				new PreAction(player, message);
			} else if (mcVersion.equals("v1_12_R1") || mcVersion.startsWith("v1_13") || mcVersion.startsWith("v1_14_")
					|| mcVersion.startsWith("v1_15_")) {
				new LegacyPreAction(player, message);
			} else if (!(mcVersion.equalsIgnoreCase("v1_8_R1") || (mcVersion.contains("v1_7_")))) {
				Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + mcVersion + ".entity.CraftPlayer");
				Object p = c1.cast(player);
				Object ppoc;
				Class<?> c4 = Class.forName("net.minecraft.server." + mcVersion + ".PacketPlayOutChat");
				Class<?> c5 = Class.forName("net.minecraft.server." + mcVersion + ".Packet");

				Class<?> c2 = Class.forName("net.minecraft.server." + mcVersion + ".ChatComponentText");
				Class<?> c3 = Class.forName("net.minecraft.server." + mcVersion + ".IChatBaseComponent");
				Object o = c2.getConstructor(new Class<?>[] { String.class }).newInstance(message);
				ppoc = c4.getConstructor(new Class<?>[] { c3, byte.class }).newInstance(o, (byte) 2);

				Method getHandle = c1.getDeclaredMethod("getHandle");
				Object handle = getHandle.invoke(p);

				Field fieldConnection = handle.getClass().getDeclaredField("playerConnection");
				Object playerConnection = fieldConnection.get(handle);

				Method sendPacket = playerConnection.getClass().getDeclaredMethod("sendPacket", c5);
				sendPacket.invoke(playerConnection, ppoc);
			} else {
				Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + mcVersion + ".entity.CraftPlayer");
				Object p = c1.cast(player);
				Object ppoc;
				Class<?> c4 = Class.forName("net.minecraft.server." + mcVersion + ".PacketPlayOutChat");
				Class<?> c5 = Class.forName("net.minecraft.server." + mcVersion + ".Packet");

				Class<?> c2 = Class.forName("net.minecraft.server." + mcVersion + ".ChatSerializer");
				Class<?> c3 = Class.forName("net.minecraft.server." + mcVersion + ".IChatBaseComponent");
				Method m3 = c2.getDeclaredMethod("a", String.class);
				Object cbc = c3.cast(m3.invoke(c2, "{\"text\": \"" + message + "\"}"));
				ppoc = c4.getConstructor(new Class<?>[] { c3, byte.class }).newInstance(cbc, (byte) 2);

				Method getHandle = c1.getDeclaredMethod("getHandle");
				Object handle = getHandle.invoke(p);

				Field fieldConnection = handle.getClass().getDeclaredField("playerConnection");
				Object playerConnection = fieldConnection.get(handle);

				Method sendPacket = playerConnection.getClass().getDeclaredMethod("sendPacket", c5);
				sendPacket.invoke(playerConnection, ppoc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
