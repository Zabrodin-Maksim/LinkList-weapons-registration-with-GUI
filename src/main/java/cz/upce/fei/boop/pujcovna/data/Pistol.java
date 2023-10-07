/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.data;


/**
 *
 * @author 42070
 */
public final class Pistol extends Weapons {

    public Pistol(String name, int id, String country, int pocetNaboj, double vaha) {
        super(name, cislo_id++, country, pocetNaboj, vaha, EnumTyp.PISTOL);
    }
    
}
