package com.ystoreplugins.yautovender.method;

import com.ystoreplugins.yautovender.Main;
import com.ystoreplugins.yautovender.model.Bloco;
import com.ystoreplugins.yautovender.model.Bonus;
import com.ystoreplugins.yautovender.util.Formatter;
import com.ystoreplugins.yautovender.util.actionbar.ActionBar;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class PlayerMethods {

    public static void sendActionbar(Player p, Bonus bonus, double money, String bloco, Main main){
        String message = bonus != null ? main.getConfig().getString("Actionbar bonus") : main.getConfig().getString("Actionbar");

        String actionbar = message
                .replace('&', '§')
                .replace("{bloco}", bloco)
                .replace("{bonus}", bonus != null ? "" + bonus.getBonus() : "0")
                .replace("{grupo}", bonus != null ? bonus.getDisplay() : "")
                .replace("{money}", Formatter.letterFormatter(money));
        ActionBar.sendActionBar(p, actionbar);
    }

    public static double getFortuneAmount(ItemStack item, Bloco bloco){
        if (!bloco.isFortune()) return 1;
        if (!item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) return 1;
        int level = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
        int pct = (2 * (level + 1));
        double i = ThreadLocalRandom.current().nextInt(1 * (level + 1), pct);
        return i > 0 ? i : 1;
    }

    public static void setDurability(Player p){
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) return;

        ItemStack pItem = p.getItemInHand();
        int unbhas = pItem.getDurability();
        int levelunb = 0;

        if (pItem.containsEnchantment(Enchantment.DURABILITY))
            levelunb = pItem.getEnchantmentLevel(Enchantment.DURABILITY) + 1;

        int i2 = ThreadLocalRandom.current().nextInt(0, levelunb + 1);
        if (i2 <= 1) pItem.setDurability((short) (unbhas + 1));

        if (pItem.getDurability() >= pItem.getType().getMaxDurability()) {
            p.setItemInHand(null);
            p.updateInventory();
            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 200, 200);
        }
    }
}
