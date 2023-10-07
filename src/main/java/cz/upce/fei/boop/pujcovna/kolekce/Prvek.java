
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.kolekce;

/**
 *
 * @author 42070
 */
public class Prvek<E> {
    private E data;
    private Prvek dalsi;

    public Prvek(E data, Prvek dalsi) {
        if(data == null) throw new NullPointerException();
        this.data = data;
        this.dalsi = dalsi;
    }

    public E getData() {
        return data;
    }


    public Prvek getDalsi() {
        return dalsi;
    }

    public void setDalsi(Prvek dalsi) {
        this.dalsi = dalsi;
    }

   
    
}
