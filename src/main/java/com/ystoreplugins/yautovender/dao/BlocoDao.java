package com.ystoreplugins.yautovender.dao;

import com.ystoreplugins.yautovender.model.Bloco;

import java.util.HashMap;

public class BlocoDao {

    private final static HashMap<String, Bloco> blocos = new HashMap<>();

    public static void add(Bloco object) {
        BlocoDao.blocos.put(object.getBloco(), object);
    }

    public static Bloco get(String object) {
        return BlocoDao.blocos.get(object);
    }

    public static boolean contains(String object) {
        return BlocoDao.blocos.containsKey(object);
    }

    public static HashMap<String, Bloco> getBlocos() {
        return blocos;
    }
}
