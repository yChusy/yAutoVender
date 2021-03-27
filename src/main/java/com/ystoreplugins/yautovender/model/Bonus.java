package com.ystoreplugins.yautovender.model;

import com.ystoreplugins.yautovender.dao.BonusDao;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bonus {

    @Getter private final String name, permissao, display;
    @Getter private final double bonus;

    public Bonus(String name, String permissao, String display, double bonus){
        this.name = name;
        this.permissao = permissao;
        this.display = display;
        this.bonus = bonus;
    }

    public static Bonus getPlayerBonus(Player p){
        Bonus toReturn = null;
        List<Bonus> bonuses = new ArrayList<>(BonusDao.getBonuses().values()
                .stream()
                .filter(bonus -> p.hasPermission(bonus.getPermissao()))
                .collect(Collectors.toList())
        );
        toReturn = bonuses.size() > 0 ? bonuses.get(bonuses.size() - 1) : null;
        return toReturn;
    }
}
