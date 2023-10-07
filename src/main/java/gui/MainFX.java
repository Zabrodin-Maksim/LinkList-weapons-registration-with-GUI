/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import cz.upce.fei.boop.pujcovna.data.EnumTyp;
import static cz.upce.fei.boop.pujcovna.data.EnumTyp.PISTOL;
import static cz.upce.fei.boop.pujcovna.data.EnumTyp.RIFLE;
import static cz.upce.fei.boop.pujcovna.data.EnumTyp.SHOTGUN;
import static cz.upce.fei.boop.pujcovna.data.EnumTyp.SNIPERRIFLE;
import cz.upce.fei.boop.pujcovna.data.Weapons;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Spliterators;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import spravce.SpravaProstredku;

/**
 *
 * @author 42070
 */
public class MainFX extends Application{
    private double sceneSizeHeigh = 720.0;
    private double sceneSizeWidth = 1200.0;
    
    EnumTyp typ = null;
    
    SpravaProstredku SpravaProstr = new SpravaProstredku();
    
    ObservableList<Weapons> weapons = FXCollections.observableArrayList();
    ListView<Weapons> WeaponsListView = new ListView<Weapons>(weapons);
    // DOWN BOX
    
    // filtr Combo Box 
    Label labelfiltr = new Label("Filtr:");
    ObservableList<String> filtrTyps = FXCollections.observableArrayList("Pistol", "Rifle", "Shoutgun", "Sniper Rifle", "ALL");
    ComboBox<String> filtrComboBox = new ComboBox<String>(filtrTyps);
    
    // novy Combo box 
    Label label1 = new Label("Novy:");
    ObservableList<String> typs = FXCollections.observableArrayList("Pistol", "Rifle", "Shoutgun", "Sniper Rifle");
    ComboBox<String> typsComboBox = new ComboBox<String>(typs);
    
    
    //Buttons down
    Button generujBtn = new Button("Generuj");
    Button ulozBtn = new Button("Ulož");
    Button nactiBtn = new Button("Načti");
    Button najdiBtn = new Button("Najdi");
    Button ZalohujBtn = new Button("Zálohuj");
    Button ObnovBtn = new Button("Obnov");
    Button ZrusBtn = new Button("Zruš");
    
    
    Label labelProch = new Label("Procházení");
    Label labelPrikazy = new Label("Příkazy");
    
    //Buttons Right
    Button prvniBtn = new Button("prvni");
    Button dalsiBtn = new Button("dalsi");
    Button predchoziBtn = new Button("predchozi");
    Button posledniBtn = new Button("posledni");
    Button editujBtn = new Button("Edituj");
    Button vyjmiBtn = new Button("Vyjmi");
    Button zobraztn = new Button("Zobraz");
    Button cleartn = new Button("Clear");
    
    static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception, FileNotFoundException, UnsupportedEncodingException {
        
        WeaponsListView.setPrefSize(1050, 870);
        
        // Actions for button generuj in down box
        generujBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialogGeneruj = new TextInputDialog();
                dialogGeneruj.setTitle("Generuj Dialog");
                dialogGeneruj.setHeaderText("Look, a Text Input Dialog");
                dialogGeneruj.setContentText("Please enter number:");
                Optional<String> result = dialogGeneruj.showAndWait();
                if (result.isPresent()){
                    String cislo = result.get();
                    cislo = cislo.trim();
                    if(Integer.parseInt(cislo) < 0){
//                        new Alert(Alert.AlertType.WARNING, "Խնդրում ենք մուտքագրել դրական թիվ!", ButtonType.OK).showAndWait();
                        new Alert(Alert.AlertType.WARNING, "Please enter a positive number", ButtonType.OK).showAndWait();
                        generujBtn.fire();
                    }else{
                        try{
                        SpravaProstr.generuj(Integer.parseInt(cislo));
                        }catch (NumberFormatException ex) {
                            new Alert(Alert.AlertType.WARNING, "Please enter number", ButtonType.OK).showAndWait();
                            generujBtn.fire();
                        }
                }
                }
                refresh();}});
        
        // Actions for button uloz in down box
        ulozBtn.setOnAction(event -> {
            try {
                SpravaProstr.uloztext();
            } catch (FileNotFoundException ex) {
                new Alert(Alert.AlertType.WARNING, "Soubor nenalezen", ButtonType.OK).show();
            } catch (UnsupportedEncodingException ex) {
                new Alert(Alert.AlertType.WARNING, "Doslo k chybe", ButtonType.OK).show();
            }
        });
        
        // Actions for button nacti in down box
        nactiBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SpravaProstr.nactitext();
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.WARNING, "Doslo k chybe", ButtonType.OK).show();
                }
                refresh();
                }});
        
        // Actions for button najdi in down box
        najdiBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialogNajdi = new TextInputDialog();
                dialogNajdi.setTitle("Najdi Dialog");
                dialogNajdi.setHeaderText("Look, a Text Input Dialog");
                dialogNajdi.setContentText("Please enter ID or Name:");
                Optional<String> result = dialogNajdi.showAndWait();
                if (result.isPresent()){
                    try{
                        WeaponsListView.getSelectionModel().select(SpravaProstr.najdi(result.get()));
                        }catch (Exception ex) {
                            new Alert(Alert.AlertType.WARNING, ex.getMessage(), ButtonType.OK).showAndWait();
                            najdiBtn.fire();
                    }
                }
                }});
        
        // Actions for button Zalohuj in down box
        ZalohujBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    try{
                       SpravaProstr.zalohuj();
                        }catch (IOException ex) {
                            new Alert(Alert.AlertType.WARNING, "Doslo k chybe", ButtonType.OK).showAndWait();
                    }
                
                }});
        
        // Actions for button Obnov in down box
        ObnovBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    try{
                       SpravaProstr.obnov();
                        }catch (IOException ex) {
                            new Alert(Alert.AlertType.WARNING, "Doslo k chybe", ButtonType.OK).showAndWait();
                    }
                refresh();
                }});
        
        
        ZrusBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SpravaProstr.zrus();
                refresh();
                }});
        
        
        // Actions for novy Check Boxs in down box 
        typsComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(typsComboBox.getValue() == null){}
                else{
                if(SpravaProstr.getSeznam().jePrazdny()){
                    new Alert(Alert.AlertType.WARNING, "Seznam je prázdný", ButtonType.OK).showAndWait();
                }else{
                    if(SpravaProstr.getSeznam().isAktualniNull()){
                        new Alert(Alert.AlertType.WARNING, "Aktuální prvek není nastaven", ButtonType.OK).showAndWait();
                    }else{
                        
                        Stage stage;
                        stage = DialogNovy.getDialogNovy(typsComboBox.getValue(), SpravaProstr);
                        stage.setResizable(false);
                        stage.showAndWait();
                        refresh();
                    }
                }
                }
                typsComboBox.setValue(null);
                }});
        
        // Actions for filtr Check Boxs in down box 
        filtrComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switch(filtrComboBox.getValue()){
                    case "Pistol" ->{
                        typ = PISTOL;
                    }
                    case "Rifle" ->{
                        typ = RIFLE;
                    }
                    case "Shoutgun" ->{
                        typ = SHOTGUN;
                    }
                    case "Sniper Rifle" ->{
                        typ = SNIPERRIFLE;
                    }
                    case "ALL" ->{
                        typ = null;
                    }
                }
                refresh();
                }});
        
        //Actions for button prvni
        prvniBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    try{
                        SpravaProstr.prvni();
                        WeaponsListView.getSelectionModel().select(SpravaProstr.dej());
                        }catch (KolekceException ex) {
                            new Alert(Alert.AlertType.WARNING, "Seznam je prazdny!!!", ButtonType.OK).showAndWait();
                    }
                }});
        
        // Actions for button dalsi
        dalsiBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    try{
                        
                        if(SpravaProstr.getSeznam().jePrazdny()){
                            new Alert(Alert.AlertType.WARNING, "Seznam je prazdny", ButtonType.OK).showAndWait();
                        }else{
                            if(SpravaProstr.getSeznam().isAktualniNull()){
                                new Alert(Alert.AlertType.WARNING, "Aktuální prvek není nastaven", ButtonType.OK).showAndWait();
                            }else{
                                SpravaProstr.dalsi();
                                WeaponsListView.getSelectionModel().select(SpravaProstr.dej());
                            }
                        }
                    }catch (KolekceException ex) {
                        new Alert(Alert.AlertType.WARNING, "Konec seznamu!!!", ButtonType.OK).showAndWait();
                    }
                }});
        
        // Actions for button prdchozi 
        predchoziBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(SpravaProstr.getSeznam().jePrazdny()){
                    new Alert(Alert.AlertType.WARNING, "Seznam je prazdny", ButtonType.OK).showAndWait();
                }else{
                    try{
                        SpravaProstr.predchozi();
                        WeaponsListView.getSelectionModel().select(SpravaProstr.dej());
                    }catch (KolekceException ex) {
                        new Alert(Alert.AlertType.WARNING, "Aktuální prvek není nastaven", ButtonType.OK).showAndWait();
                    }catch (NullPointerException ex){
                        new Alert(Alert.AlertType.WARNING, "Konec seznamu", ButtonType.OK).showAndWait();
                    }
                }
                }});
        
        //Actions for button posledni
        posledniBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    try{
                        SpravaProstr.posledni();
                        WeaponsListView.getSelectionModel().select(SpravaProstr.dej());
                        }catch (KolekceException ex) {
                            new Alert(Alert.AlertType.WARNING, "Seznam je prazdny!!!", ButtonType.OK).showAndWait();
                    }
                }});
        
        //Actions for button edituj 
        editujBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(SpravaProstr.getSeznam().jePrazdny()){
                    new Alert(Alert.AlertType.WARNING, "Seznam je prázdný", ButtonType.OK).showAndWait();
                    }else{
                        if(SpravaProstr.getSeznam().isAktualniNull()){
                            new Alert(Alert.AlertType.WARNING, "Aktuální prvek není nastaven", ButtonType.OK).showAndWait();
                        }else{
                            Stage stage;
                            stage = DialogEdit.getDialogNovy(SpravaProstr.dej().getTyp().name(),SpravaProstr.dej().getID(), SpravaProstr);
                            stage.setResizable(false);
                            stage.showAndWait();
                            refresh();
                        }
                    }
                } catch (KolekceException ex) {
                    Logger.getLogger(MainFX.class.getName()).log(Level.SEVERE, null, ex);
                }
                }});
        
        // Actions for button Vyjmi 
        vyjmiBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    try{
                        WeaponsListView.getSelectionModel().selectLast();
                        SpravaProstr.vyjmi();
                        SpravaProstr.prvni();
                        }catch (KolekceException ex) {
                            new Alert(Alert.AlertType.WARNING, "Seznam je prazdny!!!", ButtonType.OK).showAndWait();
                    }
                    refresh();
                }});
        
        //Actions for button Zobraz
        zobraztn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(SpravaProstr.getSeznam().jePrazdny()){
                    new Alert(Alert.AlertType.WARNING, "Seznam je prázdný", ButtonType.OK).showAndWait();
                }else{
                    if(SpravaProstr.getSeznam().isAktualniNull()){
                        new Alert(Alert.AlertType.WARNING, "Aktuální prvek není nastaven", ButtonType.OK).showAndWait();
                    }else{
                        Stage stage;
                        try {
                            stage = DialogZobraz.getDialogNovy(SpravaProstr.dej());
                            stage.setResizable(false);
                            stage.showAndWait();
                        } catch (KolekceException ex) {
                            Logger.getLogger(MainFX.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                }});
        
        
        cleartn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    SpravaProstr.zrus();
                    refresh();
                }});
        
        filtrComboBox.setPromptText("filtr");
        typsComboBox.setPromptText("Nový");
        HBox ButtonDownBox = new HBox(15, generujBtn, ulozBtn, nactiBtn, label1, typsComboBox, labelfiltr, filtrComboBox, najdiBtn, ZalohujBtn, ObnovBtn, ZrusBtn);
        ButtonDownBox.setTranslateX(10);
        ButtonDownBox.setTranslateY(-10);
        
        VBox vbox = new VBox(20, WeaponsListView, ButtonDownBox);
        
        VBox RightBox = new VBox(10, labelProch, prvniBtn, dalsiBtn, predchoziBtn, posledniBtn, labelPrikazy, editujBtn, vyjmiBtn, zobraztn, cleartn);
        HBox root = new HBox(20, vbox, RightBox);
        
        //Buttons PrefWidth
        prvniBtn.setPrefWidth(80);
        dalsiBtn.setPrefWidth(80);
        predchoziBtn.setPrefWidth(80);
        posledniBtn.setPrefWidth(80);
        editujBtn.setPrefWidth(80);
        vyjmiBtn.setPrefWidth(80);
        zobraztn.setPrefWidth(80);
        cleartn.setPrefWidth(80);
        
        // Scene
        Scene scene = new Scene(root, sceneSizeWidth, sceneSizeHeigh );
        stage.setResizable(false); 
        stage.setScene(scene);
         
        stage.setTitle("Pujcovna Gui Zabrodin Maksim");
         
        stage.show();
    }
    
    
    
    private void refresh() {
        WeaponsListView.getItems().clear();
        if(typ != null){
        SpravaProstredku.getSeznam().stream().filter(s -> s.getTyp() == typ).forEachOrdered(WeaponsListView.getItems()::add);
        } else{
        StreamSupport.stream(Spliterators.spliteratorUnknownSize(SpravaProstr.getSeznam().iterator(), 0), false).forEachOrdered(WeaponsListView.getItems()::add);
        }
    }
    
}
