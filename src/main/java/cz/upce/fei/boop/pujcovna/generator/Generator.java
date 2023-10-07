/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.generator;

import cz.upce.fei.boop.pujcovna.data.Pistol;
import cz.upce.fei.boop.pujcovna.data.Rifle;
import cz.upce.fei.boop.pujcovna.data.Shotgun;
import cz.upce.fei.boop.pujcovna.data.SniperRifle;
import cz.upce.fei.boop.pujcovna.data.Weapons;
import java.util.Arrays;
import java.util.Random;
import cz.upce.fei.boop.pujcovna.kolekce.LinkSeznam;

/**
 *
 * @author 42070
 */
public class Generator {
    
    private final static Random random = new Random();
    private static final String[] pistolsName = {"Beretta 92", "Glock 17", "Colt M1911", "Heckler & Koch USP", "CZ-85", "TT", "FN Five-seveN", "Walther PP", "Desert Eagle", "АПС"};
    private static final String[] rifleName = {"AK-47", "M16", "Lee-Enfield SMLE", "M1 Garand", "FN FAL", "Mauser K98k", "Steyr Aug", "1903 Springfield", "Sturmgewehr 44", "М14"};
    private static final String[] shotgunName = {"KEL-TEC KSG", "SRM ARMS MODEL 1216", "UTAS UTS-15", "«Вепрь-12»", "Mossberg 500 Tactical Persuader", "Benelli M4 Super 90", "Remington Model 870", "FN SLP", "Super Black Eagle II Shotgun", "Benelli Supernova"};
    private static final String[] sniperRifleName = {"M2010", "СВД", "L96A1", "Barrett M82", "L42 Enfield", "SR-25", "AS50", "М21", "PSG1", "L115A3 AWM"};
    public static void generuj(LinkSeznam seznam, int pocet){
        
        Weapons weapon = null;
        String name;
        String country;
        int pocetN;
        double vaha;
        
        for (int i = 0; i < pocet; i++){
            name = dejName();
            country = dejCountry();
            pocetN = dejPocetN();
            vaha = dejVaha();
            
            if (Arrays.asList(pistolsName).contains(name)){
                weapon = new Pistol(name, Weapons.cislo_id, country, pocetN, vaha);
            }
            else if (Arrays.asList(rifleName).contains(name)){
                weapon = new Rifle(name, Weapons.cislo_id, country, pocetN, vaha);
            }
            else if (Arrays.asList(shotgunName).contains(name)){
                weapon = new Shotgun(name, Weapons.cislo_id, country, pocetN, vaha);
            }
            else if (Arrays.asList(sniperRifleName).contains(name)){
                weapon = new SniperRifle(name, Weapons.cislo_id, country, pocetN, vaha);
            }
            seznam.vlozPosledni(weapon);
        }
    }
    
    private static String dejName(){
        String[] modelName = {"M2010", "СВД", "L96A1", "Barrett M82", "L42 Enfield", "SR-25", "AS50", "М21", "PSG1", "L115A3 AWM","KEL-TEC KSG", "SRM ARMS MODEL 1216", 
            "UTAS UTS-15", "«Вепрь-12»", "Mossberg 500 Tactical Persuader", "Benelli M4 Super 90", "Remington Model 870", "FN SLP", "Super Black Eagle II Shotgun", "Benelli Supernova",
            "AK-47", "M16", "Lee-Enfield SMLE", "M1 Garand", "FN FAL", "Mauser K98k", "Steyr Aug", "1903 Springfield", "Sturmgewehr 44", "М14",
            "Beretta 92", "Glock 17", "Colt M1911", "Heckler & Koch USP", "CZ-85", "TT", "FN Five-seveN", "Walther PP", "Desert Eagle", "АПС"};
        return modelName[random.nextInt(modelName.length)];
    }
    
    private static String dejCountry(){
        String[] countrysVlastnika = {"Italy", "America", "Great Britain", "France", "Germany", "Austria", "Spain"};
        return countrysVlastnika[random.nextInt(countrysVlastnika.length)];
    }
    
    private static int dejPocetN(){
        return random.nextInt(30);
    }
    
    private static double dejVaha(){
        return random.nextDouble(12);
    }
    
}
