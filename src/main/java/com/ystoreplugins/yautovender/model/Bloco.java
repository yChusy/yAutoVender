package com.ystoreplugins.yautovender.model;

import lombok.Getter;

public class Bloco {

    @Getter private final String nome, bloco, display;
    @Getter private final double money, exp, mcmmo;
    @Getter private final boolean fortune;

    public Bloco(String nome, String bloco, String display, double money, double exp, double mcmmo, boolean fortune){
        this.nome = nome;
        this.bloco = bloco;
        this.display = display;
        this.money = money;
        this.exp = exp;
        this.mcmmo = mcmmo;
        this.fortune = fortune;
    }
}
