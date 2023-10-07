/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.command;

import cz.upce.fei.boop.pujcovna.data.Pistol;
import cz.upce.fei.boop.pujcovna.data.Rifle;
import cz.upce.fei.boop.pujcovna.data.Shotgun;
import cz.upce.fei.boop.pujcovna.data.SniperRifle;
import cz.upce.fei.boop.pujcovna.data.Weapons;
import cz.upce.fei.boop.pujcovna.generator.Generator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.kolekce.LinkSeznam;
import cz.upce.fei.boop.pujcovna.perzistence.ZapisCteni;
import spravce.SpravaProstredku;

/**
 *
 * @author 42070
 */
public class CommandMain {
    private static boolean stop = true;
//    private static LinkSeznam<Weapons> seznam;
    public static void main(String[] args) throws KolekceException {
//        seznam = new LinkSeznam();
        System.out.println("Zacatek programu");
        help();
        command();
        System.out.println("Konec programu");
    }

    private static void help() {
        List<String> listCommand = Arrays.asList("\nhelp, h     - výpis příkazů",
                "novy, no     - vytvoř novou instanci a vlož data za aktuální prvek",
                "najdi, na, n  - najdi v seznamu data podle hodnoty nějakém atributu",
                "odeber, od   - odeber data ze seznamu podle nějaké hodnoty atributu",
                "dej         - zobraz aktuální data v seznamu",
                "edituj, edit - edituj aktuální data v seznamu",
                "vyjmi       - vyjmi aktuální data ze seznamu",
                "prvni, pr    - nastav jako aktuální první data v seznamu",
                "dalsi, da    - přejdi na další data",
                "posledni, po - přejdi na poslední data",
                "pocet       - zobraz počet položek v seznamu",
                "obnov       - obnov seznam data z binárního souboru",
                "zalohuj     - zálohuj seznam dat do binárního souboru",
                "vypis       - zobraz seznam dat",
                "nactitext, nt- načti seznam data z textového souboru",
                "uloztext, uz - ulož seznam data do textového souboru",
                "generuj, g   - generuj náhodně data pro testování",
                "zrus        - zruš všechny data v seznamu",
                "exit        - ukončení programu");
        listCommand.forEach(System.out::println);
    }

    private static <E> void command() throws KolekceException {
        do {
            try {
                Scanner scaner = new Scanner(System.in);
                System.out.println("Napiste comandu");
                var a = scaner.nextLine();
                SpravaProstredku sprava = new SpravaProstredku();
                switch (a) {
                    case "help", "h" ->
                        help();
                    case "novy", "no" -> {
                        if(sprava.getSeznam().isAktualniNull()){
                            System.out.println("Neni nastaven aktualni prvek");
                        }else {
                            System.out.println("Zadej typ weapon: [p] - Pistol, [r] - Rifle, [s] - Shotgun, [sr] - Sniper rifle");
                            var typ = scaner.nextLine();
                            typ = typ.trim();
                            System.out.println("Zadej nazev");
                            var name = scaner.nextLine();
                            name = name.trim();
                            System.out.println("Zadej zemi vlastníka");
                            var country = scaner.nextLine();
                            country = country.trim();
                            System.out.println("Zadej počet nábojů");
                            int pocetN = scaner.nextInt();
                            System.out.println("Zadej vahu");
                            double vaha = scaner.nextInt();
                            sprava.novy(typ, name, country, pocetN, vaha); 
                        }
                    }
                    case "najdi", "na", "n" -> {
                        System.out.println("Zadej jmeno zbraně nebo jeji ID");
                        var atribut = scaner.nextLine();
                        try{
                            atribut = atribut.trim();
                            Weapons weapon = sprava.najdi(atribut);
                            System.out.println("Byla nalezena weapon: " + weapon);
                        }catch (Exception ex){
                             System.out.println(ex.getMessage());
                        }
                    }
                    case "odeber", "od" -> {
                        try{
                            if (!sprava.getSeznam().jePrazdny()){
                                System.out.println("Zadej jmeno zbraně");
                                var name = scaner.nextLine();
                                name = name.trim();
                                sprava.odeber(name);
                            } else {
                            System.out.println("Seznam je prazdny!!!");
                            }
                        }catch (Exception ex){
                             System.out.println(ex.getMessage());
                        }
                    }
                    case "dej" -> {
                        try{
                        Weapons weapon = sprava.dej();
                        System.out.println("Aktualni weapon je \n" + weapon);
                        }
                        catch(Exception e){
                        System.out.println("Aktualni weapon neni nastaven \n");
                        }
                    }
                    case "edituj", "edit" -> {               
                        System.out.println("Zacatek editace");
                        System.out.printf("Atributy: name, country, naboj, vaha \n");
                        boolean flag = true;
                        try{
                             Weapons weapon = sprava.getSeznam().dejAktualni();
                        do {
                            System.out.println("Zadej atribut, ktery chcete zmenit, nebo exit pro ukonceni editace");
                            String atribut = scaner.nextLine().toLowerCase();
                            switch(atribut){
                            case "name" ->{
                                System.out.println("Zadej nove jmeno");
                                sprava.edituj(weapon, "name", scaner.nextLine());
                            }
                            case "country" ->{
                                System.out.println("Zadej nove zemmi");
                                sprava.edituj(weapon, "country", scaner.nextLine());
                            }
                            case "naboj" ->{
                                System.out.println("Zadej novy pocet naboje");
                                sprava.edituj(weapon, "naboj", scaner.nextLine());
                            }
                            case "vaha" -> {
                                System.out.println("Zadej novy ves");
                                sprava.edituj(weapon, "vaha", scaner.nextLine());
                            }
                            case "exit" -> {
                                flag = false;
                            }    
                            }
                        }while(flag);
                        }catch (KolekceException ex) {
                            System.out.println("Aktualni prvek neni nastaven");
                        } catch (IllegalArgumentException il) {
                            System.out.println("Neplatny atribut");
                        }
                    }
                    case "vyjmi" -> {
                        try{
                        Weapons weapon = sprava.vyjmi();
                        System.out.println("Byl odebran weapon\n" + weapon);
                        }catch(KolekceException ex){
                            System.out.println("Seznam je prazdny!!!");
                        }
                    }
                    case "prvni", "pr" -> {
                        try{
                        sprava.prvni();
                        }catch(KolekceException ex){
                            System.out.println("Seznam je prazdny!!!");
                        }
                    }
                    case "dalsi", "da" -> {
                        if(sprava.getSeznam().jePrazdny()){
                            System.out.println("Seznam je prazdny!!!");
                        }else{
                            try{
                            sprava.dalsi();
                            }catch(KolekceException ex){
                                System.out.println("Neni nastaven aktualni prvek nebo je konec seznamu");
                            }
                        }
                    }
                    case "posledni", "po" -> {
                        try{
                            sprava.posledni();
                        }catch(KolekceException ex){
                            System.out.println("Seznam je prazdny!!!");
                        }
                    }
                    case "pocet" ->{
                        int pocet = sprava.pocet();
                        System.out.println("Pocet prvku je: " + pocet);
                    }
                    case "obnov" -> {
                        if (ZapisCteni.binarni.exists()) {
                            try {
                                sprava.obnov();
                                System.out.println("Seznam byl obnoven");
                        }catch (IOException ex) {
                            Logger.getLogger(CommandMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else {
                        System.out.println("Soubor neexistuje");
                    }
                    }
                    case "zalohuj" -> {
                        try {
                            sprava.zalohuj();
                            System.out.println("Zalohovani probehlo uspesne");
                        } catch (IOException ex) {
                        Logger.getLogger(CommandMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                    case "vypis" ->{
                        if (!sprava.getSeznam().jePrazdny()){
                            sprava.vypis();
                        }else { System.out.println("Seznam je prazdny!!!");}
                    }
                    case "nactitext", "nt" -> {
                        try{
                            sprava.nactitext();
                            System.out.println("Nacteni probehlo uspesne");
                        } catch (Exception e) {
                            System.err.println("Doslo k chybe");
                        }
                    }
                    case "uloztext", "ut" -> {
                        try{
                            sprava.uloztext();
                            System.out.println("Ulozeni probehlo uspesne");
                        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                            Logger.getLogger(CommandMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    case "generuj", "g" -> {
                        System.out.println("Zadej pocet prvku");
                        int pocet = scaner.nextInt();
                        sprava.generuj(pocet);
                        System.out.println("Bylo vygenerovano " + pocet);
                    }
                    case "zrus", "z" -> {
                        sprava.zrus();
                        System.out.println("Vsechny prvky seznamu byly zruseny");
                    }
                    case "exit" ->
                        stop = false;
                    
                }

            } catch (InputMismatchException e) {
                System.out.println("Zadejte cislo prosim");
            }

        } while (stop);
    }
}
