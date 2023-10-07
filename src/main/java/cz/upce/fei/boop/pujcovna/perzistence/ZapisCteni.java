/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.perzistence;

import cz.upce.fei.boop.pujcovna.data.Pistol;
import cz.upce.fei.boop.pujcovna.data.Rifle;
import cz.upce.fei.boop.pujcovna.data.Shotgun;
import cz.upce.fei.boop.pujcovna.data.SniperRifle;
import cz.upce.fei.boop.pujcovna.data.Weapons;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import cz.upce.fei.boop.pujcovna.kolekce.LinkSeznam;

/**
 *
 * @author 42070
 */
public class ZapisCteni {
    
    public static File binarni = new File("binarni.txt");
    public static File textovy = new File("textovy.txt");
    public static <E> void uloz(String jmenoSouboru, LinkSeznam seznam) throws IOException {
        try {
            Objects.requireNonNull(seznam);

            ObjectOutputStream vystup = new ObjectOutputStream(new FileOutputStream(jmenoSouboru));
            vystup.writeInt(seznam.size());
            Iterator<E> it = seznam.iterator();
            while (it.hasNext()) {
                vystup.writeObject(it.next());
            }
            vystup.close();
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }
    
    public static <E> LinkSeznam<E> nacti(String jmenoSouboru, LinkSeznam seznam) throws IOException {
        try {
            Objects.requireNonNull(seznam);
            ObjectInputStream vstup = new ObjectInputStream(new FileInputStream(jmenoSouboru));
            seznam.zrus();

            int pocet = vstup.readInt();
            for (int i = 0; i < pocet; i++) {
                seznam.vlozPosledni((E) vstup.readObject());
            }
            vstup.close();
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException(ex);
        } finally {
        }
        return seznam;
    }
    
    static Function<Weapons, String> mapperOutput = (prostredek) -> {
        switch (prostredek.getTyp()) {
            case PISTOL -> {
                return String.format("pl, %s, %d, %s, %d, %.2f",
                        prostredek.getName(), prostredek.getID(), prostredek.getCountrysVlastnika(), prostredek.getPocetNaboj(), prostredek.getVaha());
                        
            }
            case RIFLE -> {
                return String.format("rl, %s, %d, %s, %d, %.2f",
                        prostredek.getName(), prostredek.getID(), prostredek.getCountrysVlastnika(), prostredek.getPocetNaboj(), prostredek.getVaha());

            }
            case SHOTGUN -> {
                return String.format("sl, %s, %d, %s, %d, %.2f",
                        prostredek.getName(), prostredek.getID(), prostredek.getCountrysVlastnika(), prostredek.getPocetNaboj(), prostredek.getVaha());

            }
            case SNIPERRIFLE -> {
                return String.format("srl, %s, %d, %s, %d, %.2f",
                        prostredek.getName(), prostredek.getID(), prostredek.getCountrysVlastnika(), prostredek.getPocetNaboj(), prostredek.getVaha());

            }
        }
        return null;
    };

    public static void writeStream(Stream<Weapons> stream, String nameFile) throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter pw = new PrintWriter(nameFile, "UTF-8")) {
            stream
                    .map(mapperOutput)
                    .forEachOrdered(pw::println);
        }
    }
    
    public static void readText(String nameFile, LinkSeznam seznam) {
        BufferedReader reader = null;
        String line = "";
        Weapons weapons = null;
        try {
            reader = new BufferedReader(new FileReader(nameFile));
            while ((line = reader.readLine()) != null) {
                weapons = readLine(line);
                seznam.vlozPosledni(weapons);
            }
        } catch (IOException e) {
            System.out.println("Doslo k chybe");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    private static Weapons readLine(String line) {
        Weapons prostredek = null;
        if (line.length() == 0) {
            return prostredek;
        }
        String[] items = line.split(",");
        String name = items[1].trim();
        int id = Integer.parseInt(items[2].trim());
        String country = items[3].trim();
        int pocetNaboj = Integer.parseInt(items[4].trim());
        double vaha = Double.valueOf(items[5].trim());
        switch (items[0].trim().toLowerCase(Locale.US)) {
            case "pl" -> {

                prostredek = new Pistol(name, id, country, pocetNaboj, vaha);
            }
            case "rl" -> {

                prostredek = new Rifle(name, id, country, pocetNaboj, vaha);
            }
            case "sl" -> {

                prostredek = new Shotgun(name, id, country, pocetNaboj, vaha);
            }
            case "srl" -> {

                prostredek = new SniperRifle(name, id, country, pocetNaboj, vaha);
            }
        }

        return prostredek;
    }
}
