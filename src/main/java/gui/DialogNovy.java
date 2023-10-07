/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import cz.upce.fei.boop.pujcovna.data.EnumTyp;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import spravce.SpravaProstredku;

/**
 *
 * @author 42070
 */
public class DialogNovy extends Stage{
    private String name;
    private String country;
    private int pocetNaboj;
    private double vaha;
    private String typ;
    private SpravaProstredku SpravaProstr;
    
    //Sizes Scene
    private double sceneSizeHight = 200.0;
    private double sceneSizeWight = 250.0;

    
    public static DialogNovy getDialogNovy(String typ, SpravaProstredku SpravaProstr){
        return new DialogNovy(typ, SpravaProstr);
    }
    
private DialogNovy(String typ, SpravaProstredku SpravaProstr){
    this.SpravaProstr = SpravaProstr;
    this.typ = typ;
    setTitle("Dialog - " + typ);
    initModality(Modality.APPLICATION_MODAL);
    setScene(getScene1());
}

    private Scene getScene1() {
        // Labels
        Label nazevLabel = new Label("Název");
        Label countryLabel = new Label("Country");
        Label pocetNabojLabel = new Label("Počet nabití");
        Label vahaLabel = new Label("Váha");

        //TextFields
        TextField nameField = new TextField();
        
        TextField countryField = new TextField();
        countryField.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getText();
            if (input.matches("[a-z]*") || input.matches("[A-Z]*")) {
                return change;
            }
            return null;
        }));
        
        TextField pocetNabojField = new TextField();
        pocetNabojField.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change;
            }
            return null;
        }));
        
        TextField vahaField = new TextField();
        vahaField.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getText();
            if (input.matches("[0-9]*") || input.matches("[.]*")) {
                return change;
            }
            return null;
        }));
        
        
        
        //TextFields PrefSize
        nameField.setPrefSize(150, 10);
        countryField.setPrefSize(150, 10);
        pocetNabojField.setPrefSize(150, 10);
        vahaField.setPrefSize(150, 10);
        
        //Buttons
        Button OKButton = new Button("OK");
        Button CancelButton = new Button("Cancel");
        
        //Buttons PrefWidth
        OKButton.setPrefWidth(80);
        CancelButton.setPrefWidth(80);
        
        //Action for Buttons
        OKButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if(nameField.getText() == "" || countryField.getText() == ""){
                    new Alert(Alert.AlertType.WARNING, "Zadejte Název a Country", ButtonType.OK).showAndWait();
                }else{
                name = nameField.getText();
                country = countryField.getText();
                try{
                pocetNaboj = Integer.parseInt(pocetNabojField.getText());
                vaha = Double.parseDouble(vahaField.getText());
                create();
                hide();
                }catch(NumberFormatException ex){
                    new Alert(Alert.AlertType.WARNING, "Zadejte číslo: Počet nabití a Váha", ButtonType.OK).showAndWait();
                    }
                }
            }
        });
        
        CancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                hide();
            }
        });
        
        VBox vBoxLabels = new VBox(22, nazevLabel, countryLabel, pocetNabojLabel, vahaLabel);
        VBox TextvBox = new VBox(15, nameField, countryField, pocetNabojField, vahaField);
        HBox hBox = new HBox(10, vBoxLabels, TextvBox);
        HBox hBoxButtons = new HBox(10, OKButton, CancelButton);
        VBox vBox = new VBox(20, hBox, hBoxButtons);
        
        //Translate
        TextvBox.setTranslateX(10);
        TextvBox.setTranslateY(5);
        vBoxLabels.setTranslateX(10);
        vBoxLabels.setTranslateY(10);
        hBoxButtons.setTranslateX(63);
        
        vBox.setPrefSize(sceneSizeWight, sceneSizeHight);
        return new Scene(vBox);
    }
    
    private void create(){
        try{
        switch (typ){
            case "Pistol" -> {
                SpravaProstr.novy("p", name, country, pocetNaboj, vaha);
                break;
            }

            case "Rifle" -> {
                SpravaProstr.novy("r", name, country, pocetNaboj, vaha);
                break;
            }
            case "Shoutgun" -> {
                SpravaProstr.novy("s", name, country, pocetNaboj, vaha);
                break;
            }
            case "Sniper Rifle" -> {
                SpravaProstr.novy("rs", name, country, pocetNaboj, vaha);
                break;
            }
        }
        } 
        catch (KolekceException ex) {
                    new Alert(Alert.AlertType.WARNING, "Aktuální prvek není nastaven", ButtonType.OK).showAndWait();
                }
    }
}
