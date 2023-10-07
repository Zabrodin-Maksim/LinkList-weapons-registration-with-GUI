/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import cz.upce.fei.boop.pujcovna.data.Weapons;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spravce.SpravaProstredku;

/**
 *
 * @author 42070
 */
public class DialogZobraz extends Stage{
    private Weapons weapon;
    
    //Sizes Scene
    private double sceneSizeHight = 250.0;
    private double sceneSizeWight = 250.0;

    
    public static DialogZobraz getDialogNovy(Weapons weapon) throws KolekceException{
        return new DialogZobraz( weapon);
    }
    
private DialogZobraz(Weapons weapon) throws KolekceException{
    this.weapon = weapon;
    setTitle("Dialog - " + weapon.getTyp().name());
    initModality(Modality.APPLICATION_MODAL);
    setScene(getScene1());
}

    private Scene getScene1() throws KolekceException {
        // Labels
        Label IDLabel = new Label("ID:");
        Label TypLabel = new Label("Typ:");
        Label nazevLabel = new Label("Název:");
        Label countryLabel = new Label("Country:");
        Label pocetNabojLabel = new Label("Počet nabití:");
        Label vahaLabel = new Label("Váha:");

        //Text
        Label ID = new Label(String.valueOf(weapon.getID()));
        Label typ = new Label(weapon.getTyp().name());
        Label name = new Label(weapon.getName());
        Label country = new Label(weapon.getCountrysVlastnika());
        Label pocetNaboj = new Label(String.valueOf(weapon.getPocetNaboj()));
        Label vaha = new Label(String.valueOf(weapon.getVaha()));
        
        //Buttons
        Button OKButton = new Button("OK");
        
        //Buttons PrefWidth
        OKButton.setPrefWidth(80);
        
        //Action for Buttons
        OKButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t){ hide();}});
        
        VBox vBoxLabels = new VBox(15, IDLabel, TypLabel, nazevLabel, countryLabel, pocetNabojLabel, vahaLabel);
        VBox TextvBox = new VBox(15, ID, typ, name, country, pocetNaboj, vaha);
        HBox hBox = new HBox(20, vBoxLabels, TextvBox);
        HBox hBoxButtons = new HBox(10, OKButton);
        VBox vBox = new VBox(30, hBox, hBoxButtons);
        
        //Translate
        TextvBox.setTranslateX(10);
        TextvBox.setTranslateY(10);
        vBoxLabels.setTranslateX(10);
        vBoxLabels.setTranslateY(10);
        hBoxButtons.setTranslateX(80);
        
        vBox.setPrefSize(sceneSizeWight, sceneSizeHight);
        return new Scene(vBox);
    }
    
   
    
}
