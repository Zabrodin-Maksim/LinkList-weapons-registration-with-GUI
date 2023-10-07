/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.data;

import java.io.Serializable;

/**
 *
 * @author 42070
 */
public abstract sealed class Weapons implements Serializable permits Pistol, Rifle, Shotgun, SniperRifle{
    public static int cislo_id = 1;
    
    private double vaha;
    private String name;
    private String countrysVlastnika;
    private int pocetNaboj;
    private final int ID;
    private final EnumTyp typ;

    public Weapons(String name, int id, String countrysVlastnika, int pocetNaboj, double vaha, EnumTyp typ) {
        this.vaha = vaha;
        this.ID = id;
        this.name = name;
        this.countrysVlastnika = countrysVlastnika;
        this.pocetNaboj = pocetNaboj;
        this.typ = typ;
    }

    public static int getCislo_id() {
        return cislo_id;
    }

    public static void setCislo_id(int cislo_id) {
        Weapons.cislo_id = cislo_id;
    }

    public double getVaha() {
        return vaha;
    }

    public void setVaha(double vaha) {
        this.vaha = vaha;
    }

    public int getID() {
        return ID;
    }

    public EnumTyp getTyp() {
        return typ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountrysVlastnika() {
        return countrysVlastnika;
    }

    public void setCountrysVlastnika(String countrysVlastnika) {
        this.countrysVlastnika = countrysVlastnika;
    }

    public int getPocetNaboj() {
        return pocetNaboj;
    }

    public void setPocetNaboj(int pocetNaboj) {
        this.pocetNaboj = pocetNaboj;
    }

    @Override
    public String toString() {
        return String.format("Weapon" + ", name=" + name + ", countrysVlastnika=" + countrysVlastnika + ", pocetNaboj=" + pocetNaboj + ", ID=" + ID + ", typ=" + typ + " vaha= %.2f", vaha);
    }
    
    
    
}
