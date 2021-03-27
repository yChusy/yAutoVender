package com.ystoreplugins.yautovender.dao;

import com.ystoreplugins.yautovender.model.Bonus;

import java.util.HashMap;

public class BonusDao {

    private final static HashMap<String, Bonus> bonuses = new HashMap<>();

    public static void add(Bonus object) {
        BonusDao.bonuses.put(object.getName(), object);
    }

    public static Bonus get(String object) {
        return BonusDao.bonuses.get(object);
    }

    public static boolean contains(String object) {
        return BonusDao.bonuses.containsKey(object);
    }

    public static HashMap<String, Bonus> getBonuses() {
        return bonuses;
    }
}
