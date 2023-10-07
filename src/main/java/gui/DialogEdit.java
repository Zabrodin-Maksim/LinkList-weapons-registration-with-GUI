/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

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
public class DialogEdit extends Stage{
    private int ID;
    private String name;
    private String country;
    private String pocetNaboj;
    private String vaha;
    private String typ;
    private SpravaProstredku SpravaProstr;
    
    //Sizes Scene
    private double sceneSizeHight = 250.0;
    private double sceneSizeWight = 250.0;

    
    public static DialogEdit getDialogNovy(String typ, int ID, SpravaProstredku SpravaProstr) throws KolekceException{
        return new DialogEdit(typ, ID, SpravaProstr);
    }
    
private DialogEdit(String typ, int ID, SpravaProstredku SpravaProstr) throws KolekceException{
    this.ID = ID;
    this.SpravaProstr = SpravaProstr;
    this.typ = typ;
    setTitle("Dialog - " + typ);
    initModality(Modality.APPLICATION_MODAL);
    setScene(getScene1());
}

    private Scene getScene1() throws KolekceException {
        // Labels
        Label IDcisloLabel = new Label(String.valueOf(ID));
        Label IDLabel = new Label("ID:");
        Label nazevLabel = new Label("Název");
        Label countryLabel = new Label("Country");
        Label pocetNabojLabel = new Label("Počet nabití");
        Label vahaLabel = new Label("Váha");

        //TextFields
        TextField nameField = new TextField();
        nameField.setText(SpravaProstr.dej().getName());
        
        TextField countryField = new TextField();
        countryField.setText(SpravaProstr.dej().getCountrysVlastnika());
        countryField.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getText();
            if (input.matches("[a-z]*") || input.matches("[A-Z]*")) {
                return change;
            }
            return null;
        }));
        
        TextField pocetNabojField = new TextField();
        pocetNabojField.setText(String.valueOf(SpravaProstr.dej().getPocetNaboj()));
        pocetNabojField.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change;
            }
            return null;
        }));
        
        TextField vahaField = new TextField();
        vahaField.setText(String.format(Locale.ENGLISH, "%.2f", SpravaProstr.dej().getVaha()));
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
                
                pocetNaboj = pocetNabojField.getText();
                vaha = vahaField.getText();
                edit();
                hide();
                
                }
            }
        });
        
        CancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                hide();
            }
        });
        
        VBox vBoxLabels = new VBox(22, IDLabel, nazevLabel, countryLabel, pocetNabojLabel, vahaLabel);
        VBox TextvBox = new VBox(15, IDcisloLabel,  nameField, countryField, pocetNabojField, vahaField);
        HBox hBox = new HBox(10, vBoxLabels, TextvBox);
        HBox hBoxButtons = new HBox(10, OKButton, CancelButton);
        VBox vBox = new VBox(30, hBox, hBoxButtons);
        
        //Translate
        TextvBox.setTranslateX(10);
        TextvBox.setTranslateY(12);
        vBoxLabels.setTranslateX(10);
        vBoxLabels.setTranslateY(10);
        hBoxButtons.setTranslateX(63);
        
        vBox.setPrefSize(sceneSizeWight, sceneSizeHight);
        return new Scene(vBox);
    }
    
    private void edit(){
        try{
            SpravaProstr.edituj(SpravaProstr.dej(), "name", name);
            SpravaProstr.edituj(SpravaProstr.dej(), "country", country);
            SpravaProstr.edituj(SpravaProstr.dej(), "naboj", pocetNaboj);
            SpravaProstr.edituj(SpravaProstr.dej(), "vaha", vaha);
        } 
        catch (KolekceException ex) {
                    new Alert(Alert.AlertType.WARNING, "Aktuální prvek není nastaven", ButtonType.OK).showAndWait();
                }
        
    }
    
}
