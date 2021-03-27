package com.ystoreplugins.yautovender.listener;

import com.gmail.nossr50.api.ExperienceAPI;
import com.ystoreplugins.yautovender.Main;
import com.ystoreplugins.yautovender.dao.BlocoDao;
import com.ystoreplugins.yautovender.method.PlayerMethods;
import com.ystoreplugins.yautovender.model.Bloco;
import com.ystoreplugins.yautovender.model.Bonus;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    private final Main main;

    public BlockBreak(Main main){
        this.main = main;
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        String brokenBlock = e.getBlock().getTypeId() + ":" + e.getBlock().getData();
        if (main.getConfig().getStringList("Blacklisted worlds").contains(p.getWorld().getName())) return;
        if (!BlocoDao.contains(brokenBlock)) return;
        if (p.getGameMode() == GameMode.CREATIVE) return;

        Bloco bloco = BlocoDao.get(brokenBlock);
        Bonus bonusObject = Bonus.getPlayerBonus(p);
        double bonus = bonusObject != null ? bonusObject.getBonus() : 0;
        double blockAmount = PlayerMethods.getFortuneAmount(p.getItemInHand(), bloco);
        double money = (blockAmount * bloco.getMoney()) + (bonus * bloco.getMoney() / 100);

        e.setCancelled(true);
        e.getBlock().setType(Material.AIR);

        p.giveExp((int) bloco.getExp());
        main.getEcon().depositPlayer(p, money);
        PlayerMethods.setDurability(p);
        PlayerMethods.sendActionbar(p, bonusObject, money, bloco.getDisplay(), main);

        if (bloco.getMcmmo() > 0 && Bukkit.getPluginManager().isPluginEnabled("mcMMO"))
            ExperienceAPI.addRawXP(p, "MINING", (float) bloco.getMcmmo());
    }

}
