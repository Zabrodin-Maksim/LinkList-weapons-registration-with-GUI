/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.data;

/**
 *
 * @author 42070
 */
public enum EnumTyp {
    PISTOL("pistol"),
    RIFLE("pistol"),
    SHOTGUN("shothun"),
    SNIPERRIFLE("sniper rifle");
    
    private final String nazev;
    
    private EnumTyp(String nazev){
        this.nazev = nazev;
    }
    
}
