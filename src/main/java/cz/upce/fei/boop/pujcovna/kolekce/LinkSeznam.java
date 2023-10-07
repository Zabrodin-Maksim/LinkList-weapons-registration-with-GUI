/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkSeznam<E> implements Seznam<E> {

    private Prvek<E> prvni;
    private Prvek<E> posledni;
    private Prvek<E> aktualni;
    private int size;

    public LinkSeznam() {
        this.size = 0;
        this.prvni = null;
        this.posledni = null;
        this.aktualni = null;
    }

    

    private static class Prvek<E> {

        E hodnota;
        Prvek<E> dalsi;

        private Prvek(E hodnota, Prvek<E> dalsiPrvek) {
            this.hodnota = hodnota;
            this.dalsi = dalsiPrvek;
        }

    }

    @Override
    public void nastavPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        aktualni = prvni;

    }

    @Override
    public void nastavPosledni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        aktualni = posledni;

    }

    @Override
    public void dalsi() throws KolekceException {
        if (!jeDalsi() || isAktualniNull()) {
            throw new KolekceException();
        }
        aktualni = aktualni.dalsi;
    }
    
    @Override
    public void predchozi() throws KolekceException {
        if (isAktualniNull()) {
            throw new KolekceException();
        }
        Prvek<E> pc = prvni;
        while(pc.dalsi != aktualni){
            pc = pc.dalsi;
        }
        aktualni = pc;
    }
    
    @Override
    public boolean jeDalsi() {
        if (jePrazdny() || isAktualniNull()) {
            return false;
        }
        return aktualni.dalsi != null;
    }

    @Override
    public void vlozPrvni(E data) {
        if (isDataNull(data)) {
            throw new NullPointerException();
        }
        prvni = new Prvek<>(data, prvni);
        if (jePrazdny()) {
            posledni = prvni;
        }
        size++;

    }

    @Override
    public void vlozPosledni(E data) {
        if (isDataNull(data)) {
            throw new NullPointerException();
        }
        Prvek<E> novyPrvek = new Prvek<>(data, null);
        if (jePrazdny()) {
            prvni = novyPrvek;
        } else {
            posledni.dalsi = novyPrvek;
        }
        posledni = novyPrvek;
        size++;
    }

    @Override
    public void vlozZaAktualni(E data) throws KolekceException {
        if (isDataNull(data)) {
            throw new NullPointerException();
        }
        if (isAktualniNull()) {
            throw new KolekceException();
        }
        Prvek<E> novyPrvek = new Prvek<>(data, null);
        if (jeDalsi()) {
            novyPrvek.dalsi = aktualni.dalsi;
            aktualni.dalsi = novyPrvek;
            
            size++;
        } else {
            vlozPosledni(data);
        }
    }

    @Override
    public boolean jePrazdny() {
        return size == 0;
    }

    @Override
    public E dejPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        return prvni.hodnota;
    }

    @Override
    public E dejPosledni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        return posledni.hodnota;
    }

    @Override
    public E dejAktualni() throws KolekceException {
        if (jePrazdny() || isAktualniNull()) {
            throw new KolekceException();
        }
        return aktualni.hodnota;
    }

    @Override
    public E dejZaAktualnim() throws KolekceException {
        if (jePrazdny() || isAktualniNull()) {
            throw new KolekceException();
        }
        if (jeDalsi()) {
            return aktualni.dalsi.hodnota;
        } else {
            throw new KolekceException();
        }
    }

    @Override
    public E odeberPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        E vysledek = prvni.hodnota;
        if (aktualni == prvni) {
            aktualni = null;
        }
        prvni = prvni.dalsi;
        size--;
        if (jePrazdny()) {
            posledni = null;
        }
        return vysledek;
    }

    @Override
    public E odeberPosledni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        E vysledek = posledni.hodnota;
        if (aktualni == posledni) {
            aktualni = null;
        }
        if (prvni == posledni) {
            prvni = null;
            posledni = null;
        } else {
            Prvek<E> prechodovy = prvni;
            while (prechodovy.dalsi.dalsi != null) {
                prechodovy = prechodovy.dalsi;
            }
            posledni = prechodovy;
            prechodovy.dalsi = null;
        }
        size--;
        return vysledek;
    }

    @Override
    public E odeberAktualni() throws KolekceException {
        if (jePrazdny() || aktualni == null) {
            throw new KolekceException();
        }
        E vysledek = aktualni.hodnota;
        if (aktualni == prvni) {
            odeberPrvni();
        } else if (aktualni == posledni) {
            odeberPosledni();
        } else {
            Prvek<E> prechodovy = prvni;
            while (prvni.dalsi != aktualni) {
                prvni = prvni.dalsi;
            }
            prvni.dalsi = aktualni.dalsi;
            prvni = prechodovy;
            aktualni = null;
            size--;
        }
        return vysledek;
    }

    @Override
    public E odeberZaAktualnim() throws KolekceException {
        if (jePrazdny() || aktualni == null) {
            throw new KolekceException();
        }
        if (jeDalsi()) {
            E vysledek = aktualni.dalsi.hodnota;
            Prvek<E> temp = aktualni;
            dalsi();
            odeberAktualni();
            aktualni = temp;
            return vysledek;
        } else {
            throw new KolekceException();
        }

    }

    @Override
    public int size() {
        return this.size;
    }

    public boolean isDataNull(E data) {
        return data == null;
    }

    public boolean isAktualniNull() {
        return aktualni == null;
    }

    @Override
    public void zrus() {
        Prvek<E> prechodovy;
        while (prvni != null) {
            prechodovy = prvni;
            prvni = prvni.dalsi;
            prechodovy = null;
        }
        aktualni = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() throws NoSuchElementException {
        return new Iterator<E>() {
            private Prvek<E> temp = prvni;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = temp.hodnota;
                    temp = temp.dalsi;
                    return data;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };

    }

}
