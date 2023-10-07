/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spravce;

import cz.upce.fei.boop.pujcovna.data.Weapons;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.kolekce.LinkSeznam;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author 42070
 */
interface Ovladani {
    
    /*
    * Metoda vytvoř novou instanci a vlož data za aktuální prvek.
    * Metoda zkontroluje, zda je nastaven aktualni prvek,
    * pokud ne, napíše o tom.
    * Pokud ano,uživatel zadavá různé vlastnosti,
    * a poté metoda vytvoří nový objekt weapon. 
    * Pokud Aktualni nastaven metoda vloží nový weapon do seznamu
    */
    
    public void novy(String typs, String name, String country, int pocetN, double vaha) throws KolekceException;
    /*
    * Metoda najdi v seznamu data podle hodnoty nějakém atributu
    * Uživatel musí zadat ID nebo jmeno prvku, metoda hleda ze seznamu požadovaný prvek s atributem které zadal uživatel
    */

    public Weapons najdi(String atribut) throws Exception;
    
    /*
    * Metoda odeber data ze seznamu podle nějaké hodnoty atributu
    * Metoda ověřuje zda jsou v seznamu prvky nebo seznam je prazdny,
    * Uživatel musí zadat jmeno prvka kterého chcí odebrat ze seznamu,
    * pak metoda hledá v seznamu požadovaný prvek a odstraní jej, pak zapíše do konzole weapon které odobral
    */
    public Weapons odeber(String name) throws KolekceException, Exception;
    
    /*
    * Metoda zobraz aktuální data v seznamu
    * Metoda zapíše do konzole aktualní prvek, pokud on nastaven, pokud ne, vypíše o tom zpravu
    */
    public Weapons dej() throws KolekceException;
    
    /*
    * Metoda edituj aktuální data v seznamu
    * Uživatel musí vybrat atribut který chcí změnit
    * Metoda změní atribut podle vyberu
    */
    public void edituj(Weapons weapon, String atribut, String value) throws KolekceException;
    
    /*
    * Metoda vyjmi aktuální data ze seznamu
    */
    public Weapons vyjmi() throws KolekceException;
    
    /*
    * Metoda nastav jako aktuální první data v seznamu
    */
    public void prvni() throws KolekceException;
    
    /*
    * Metoda přejdi na další data
    */
    public void dalsi() throws KolekceException;
    
    /*
    * Metoda přejdi na predchozi data
    */
    public void predchozi() throws KolekceException;
    
    /*
    * Metoda přejdi na poslední data
    */
    public void posledni() throws KolekceException;
    
    /*
    * Metoda zobraz počet položek v seznamu
    */
    public int pocet();
    
    /*
    * Metoda obnov seznam data z binárního souboru
    */
    public void obnov() throws IOException;
    
    /*
    * Metoda zálohuj seznam dat do binárního souboru
    */
    public void zalohuj() throws IOException;
    
    /*
    * Metoda zobraz seznam dat
    */
    public LinkSeznam<Weapons> vypis();
    
    /*
    * Metoda načti seznam data z textového souboru
    */
    public void nactitext() throws Exception;
    
    /*
    * Metoda ulož seznam data do textového souboru
    */
    public void uloztext() throws FileNotFoundException, UnsupportedEncodingException;
    
    /*
    * Metoda generuj náhodně data pro testování
    */
    public void generuj(int pocet);
    
    /*
    * Metoda zruš všechny data v seznamu
    */
    public void zrus();
    
        
    
}
