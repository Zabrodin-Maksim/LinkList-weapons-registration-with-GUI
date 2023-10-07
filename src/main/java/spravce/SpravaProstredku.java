/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spravce;

import cz.upce.fei.boop.pujcovna.command.CommandMain;
import cz.upce.fei.boop.pujcovna.data.Pistol;
import cz.upce.fei.boop.pujcovna.data.Rifle;
import cz.upce.fei.boop.pujcovna.data.Shotgun;
import cz.upce.fei.boop.pujcovna.data.SniperRifle;
import cz.upce.fei.boop.pujcovna.data.Weapons;
import cz.upce.fei.boop.pujcovna.generator.Generator;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.kolekce.LinkSeznam;
import cz.upce.fei.boop.pujcovna.perzistence.ZapisCteni;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Spliterators;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

/**
 *
 * @author 42070
 */
public class SpravaProstredku implements Ovladani{
    
    private static boolean stop = true;
    private static LinkSeznam<Weapons> seznam = new LinkSeznam();;
    
   
    
    /*
    * Metoda vytvoř novou instanci a vlož data za aktuální prvek.
    * Metoda zkontroluje, zda je nastaven aktualni prvek,
    * pokud ne, napíše o tom.
    * Pokud ano,uživatel zadavá různé vlastnosti,
    * a poté metoda vytvoří nový objekt weapon. 
    * Pokud Aktualni nastaven metoda vloží nový weapon do seznamu
    */
    @Override
    public void novy(String typs, String name, String country, int pocetN, double vaha) throws KolekceException{
            Weapons weapon = null;
            switch(typs){
                case "p" -> weapon = new Pistol(name, Weapons.cislo_id, country, pocetN, vaha);
                case "r" -> weapon = new Rifle(name, Weapons.cislo_id, country, pocetN, vaha);
                case "s" -> weapon = new Shotgun(name, Weapons.cislo_id, country, pocetN, vaha);
                case "sr" -> weapon = new SniperRifle(name, Weapons.cislo_id, country, pocetN, vaha);
                }
            seznam.vlozZaAktualni(weapon);
            
    }
    
    /*
    * Metoda najdi v seznamu data podle hodnoty nějakém atributu
    * Uživatel musí zadat ID nebo jmeno prvku, metoda hleda ze seznamu požadovaný prvek s atributem které zadal uživatel
    */
    @Override
    public Weapons najdi(String atribut) throws Exception{
    try{
        for (Weapons weapon : seznam) {
            if (weapon.getID() == Integer.parseInt(atribut)) {
                return weapon;
            }
        }
    }
    catch (NumberFormatException e){
        for (Weapons weapon : seznam) {
            if (weapon.getName().equals(atribut)) {
                return weapon; 
            }
        }
    }
    throw new Exception("Not found");
    }
    
    /*
    * Metoda odeber data ze seznamu podle nějaké hodnoty atributu
    * Metoda ověřuje zda jsou v seznamu prvky nebo seznam je prazdny,
    * Uživatel musí zadat jmeno prvka kterého chcí odebrat ze seznamu,
    * pak metoda hledá v seznamu požadovaný prvek a odstraní jej, pak zapíše do konzole weapon které odobral
    */
    @Override
    public Weapons odeber(String name) throws KolekceException, Exception{
            seznam.nastavPrvni();
            for (Weapons weapon : seznam) {
                if (weapon.getName().equals(name)) {
                    seznam.odeberAktualni();
                    return weapon;
                }
                seznam.dalsi();
            }
    throw new Exception("Not found");
    }
    
    /*
    * Metoda zobraz aktuální data v seznamu
    * Metoda zapíše do konzole aktualní prvek, pokud on nastaven, pokud ne, vypíše o tom zpravu
    */
    @Override
    public Weapons dej() throws KolekceException  {
        return seznam.dejAktualni();
    }

    /*
    * Metoda edituj aktuální data v seznamu
    * Uživatel musí vybrat atribut který chcí změnit
    * Metoda změní atribut podle vyberu
    */
    @Override
    public void edituj(Weapons weapon, String atribut, String value) {
        value = value.trim();
        switch(atribut){
            case "name" ->{
                weapon.setName(value);
            }
            case "country" ->{
                weapon.setCountrysVlastnika(value);
            }
            case "naboj" ->{
                int i = Integer.parseInt(value);
                weapon.setPocetNaboj(i);
            }
            case "vaha" -> {
                double i = Double.parseDouble(value);
                weapon.setVaha(i);
            }
            default -> {
                throw new IllegalArgumentException();
                }    
            }
    }

    /*
    * Metoda vyjmi aktuální data ze seznamu
    * 
    */
    @Override
    public Weapons vyjmi() throws KolekceException{
            return seznam.odeberAktualni();
    }

    /*
    * Metoda nastav jako aktuální první data v seznamu
    *
    */
    @Override
    public void prvni() throws KolekceException{
        seznam.nastavPrvni();
    }

    /*
    * Metoda přejdi na další data
    *
    */
    @Override
    public void dalsi() throws KolekceException{
        seznam.dalsi();
    }

    /*
    * Metoda přejdi na poslední data
    *
    */
    @Override
    public void posledni() throws KolekceException{
        seznam.nastavPosledni();
    }

    /*
    * Metoda zobraz počet položek v seznamu
    *
    */
    @Override
    public int pocet() {
        return seznam.size();
    }

    /*
    * Metoda obnov seznam data z binárního souboru
    * 
    */
    @Override
    public void obnov() throws IOException{
        seznam = ZapisCteni.nacti(ZapisCteni.binarni.getPath(), seznam);
    }

    /*
    * Metoda zálohuj seznam dat do binárního souboru
    *
    */
    @Override
    public void zalohuj() throws IOException {
        ZapisCteni.uloz(ZapisCteni.binarni.getPath(), seznam);
    }

    /*
    * Metoda zobraz seznam dat
    * 
    */
    @Override
    public LinkSeznam<Weapons> vypis() {
         StreamSupport.stream(Spliterators.spliteratorUnknownSize(seznam.iterator(), 0), false)
                .forEachOrdered(System.out::println);
         return seznam;
    }

    /*
    * Metoda načti seznam data z textového souboru
    *
    */
    @Override
    public void nactitext() throws Exception{
        ZapisCteni.readText(ZapisCteni.textovy.getPath(), seznam);
    }

    /*
    * Metoda ulož seznam data do textového souboru
    *
    */
    @Override
    public void uloztext() throws FileNotFoundException, UnsupportedEncodingException{
        ZapisCteni.writeStream(seznam.stream(), ZapisCteni.textovy.getPath());
    }

    /*
    * Metoda generuj náhodně data pro testování
    *
    */
    @Override
    public void generuj(int pocet) {
        Generator.generuj(seznam, pocet);
    }

    /*
    * Metoda zruš všechny data v seznamu
    *
    */
    @Override
    public void zrus() {
        seznam.zrus();
        Weapons.cislo_id = 1;
    }

    public static LinkSeznam<Weapons> getSeznam() {
        return seznam;
    }

    @Override
    public void predchozi() throws KolekceException {
        seznam.predchozi();
    }
    
    
    
    
}
