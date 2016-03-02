/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivial;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author DAW13
 */
public class Interface {
    
    public static Scene menuAdd(Scene menu, Stage window, double width, double height) {
        //Menu añadir
        Scene add;
        Label titulo = new Label("Menu añadir");
        titulo.setId("titulo");
        Button buttonM = new Button("Volver al menú");
        buttonM.setOnAction(e -> window.setScene(menu));
        VBox boxAdd = new VBox(20);
        boxAdd.getChildren().addAll(titulo, add(1), buttonM);
        boxAdd.setAlignment(Pos.CENTER);
        boxAdd.setPrefWidth(300);
        boxAdd.setMaxWidth(500);
        boxAdd.setMaxHeight(600);
        boxAdd.setId("caja");
        add = new Scene(boxAdd, 800, 800);
        String css2 = Menu.class.getResource("addMenu.css").toExternalForm();
        add.getStylesheets().add(css2);
        return add;
    }
    
    public static Scene menuShow (Scene menu, Stage window, double width, double height) {
        Scene show;
        Label titulo = new Label("Menú Mostrar Preguntas");
        TableView<Tabla> table = new TableView();;
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn thId = new TableColumn("Id");
        thId.setCellValueFactory(new PropertyValueFactory<>("id"));
        thId.setMinWidth(20);
        TableColumn thType = new TableColumn("Tipo");
        thType.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn thCat = new TableColumn("Categoria");
        thCat.setCellValueFactory(new PropertyValueFactory<>("cat"));
        TableColumn thQuestion = new TableColumn("Pregunta");
        thQuestion.setMinWidth(350);
        thQuestion.setCellValueFactory(new PropertyValueFactory<>("question"));
        TableColumn thAnswer = new TableColumn("Respuesta");
        thAnswer.setMinWidth(300);
        thAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        TableColumn thDel = new TableColumn("Borrado");
        thDel.setCellValueFactory(new PropertyValueFactory<>("del"));
        table.setItems(getTablaData());
        table.getColumns().addAll(thId, thType, thCat, thQuestion, thAnswer, thDel);
        Button buttonSend = new Button("Mostrar");
        buttonSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tablaData.removeAll(getTablaData()); 
                Trivial.read();
            }
        });
        table.setEditable(false);
        Button buttonMenu = new Button("Volver al menú");
        buttonMenu.setOnAction(e -> window.setScene(menu));
        buttonMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tablaData.removeAll(getTablaData());
            }
        });
        VBox menuShow = new VBox(20);
        menuShow.setSpacing(5);
        menuShow.setPadding(new Insets(10, 0, 0, 10));
        menuShow.getChildren().addAll(table, buttonSend, buttonMenu);
        show = new Scene(menuShow, 1000, 800);
        return show;
    }
    
    public static Scene menuLookFor (Scene menu, Stage window, double width, double height) {
        Scene LK;
        Label titulo = new Label("Menú Buscar por ID");
        titulo.setId("titulo");
        Label espaciovacio = new Label("");
        Button buttonMenu = new Button("Volver al menú");
        buttonMenu.setOnAction(e -> window.setScene(menu));
        VBox menuLK = new VBox(); 
                
        menuLK.getChildren().addAll(titulo, search(), espaciovacio, buttonMenu);
        menuLK.setAlignment(Pos.CENTER);
        menuLK.setPrefWidth(300);
        menuLK.setMaxWidth(700);
        menuLK.setMaxHeight(700);
        LK = new Scene(menuLK, 800, 800);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        LK.getStylesheets().add(css);
        return LK;
    }
    
    public static Scene menuModify(Scene menu, Stage window, double width, double height) {
        Scene modify;
        Label titulo = new Label("Menú Modificar");
        titulo.setId("titulo");
        Button buttonMenu = new Button("Volver al menú");
        buttonMenu.setOnAction(e -> window.setScene(menu));
        GridPane paneModify = new GridPane();
        paneModify.add(add(2),0,0);
        ColumnConstraints col1 = new ColumnConstraints(400,550,550);
        paneModify.getColumnConstraints().add(0, col1);
        GridPane paneModify2 = new GridPane();
        paneModify2.add(search(),0,0);
        paneModify2.getColumnConstraints().add(0, col1);
        paneModify.setId("cajaMod");
        HBox menus = new HBox(50);
        menus.getChildren().addAll(paneModify2, paneModify);
        menus.setAlignment(Pos.CENTER);
        VBox menuShow = new VBox(20);
        menuShow.getChildren().addAll(titulo,menus, buttonMenu);
        menuShow.setAlignment(Pos.CENTER);
        modify = new Scene(menuShow, 1100, 800);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        modify.getStylesheets().add(css);
        return modify;
    }
    
    public static Scene menuDel(Scene menu, Stage window, double width, double height) {
        Scene del;
        File f = new File("Preguntas.dat");
        Label titulo = new Label("Menú Borrar");
        titulo.setId("titulo");
        Label labelDel = new Label("Introduce el ID de la pregunta "
                + "para borrar o recuperar.");
        TextField textDel = new TextField();
        textDel.setMaxWidth(100);
        Button buttonDel = new Button("Borrar");
        buttonDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int id = 0;
                if (textDel.getText().isEmpty()) Library.getAlert("La casilla del ID está vacío");
                else id = Integer.parseInt(textDel.getText());
                if (id > 0 && id <= Library.getId(f, Preguntas.getSpace())) Trivial.toDelete(id, 1);
                else Library.getAlert("Esa pregunta con ese ID no existe");
                textDel.clear(); // Para borrar el campo una vez enviado
            }
        });
        Button buttonRec = new Button("Recuperar");
        buttonDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int id = 0;
                if (textDel.getText().isEmpty()) Library.getAlert("La casilla del ID está vacío");
                else id = Integer.parseInt(textDel.getText());
                if (id > 0 && id <= Library.getId(f, Preguntas.getSpace())) Trivial.toDelete(id, 2);
                else Library.getAlert("Esa pregunta con ese ID no existe");
                textDel.clear(); // Para borrar el campo una vez enviado
            }
        });
        HBox botones = new HBox(20);
        GridPane paneDel = new GridPane();
        paneDel.add(search(),0,0);
        ColumnConstraints col1 = new ColumnConstraints(400,550,550);
        paneDel.getColumnConstraints().add(0, col1);
        GridPane paneDel2 = new GridPane();
        paneDel2.setHgap(10);
        paneDel2.setVgap(12);
        paneDel2.add(labelDel,0,0);
        paneDel2.add(textDel, 3, 1);
        paneDel2.getColumnConstraints().add(0, col1);
        paneDel2.setMaxHeight(300);
        paneDel2.setId("cajaMod");
        paneDel2.setAlignment(Pos.CENTER);
        botones.getChildren().addAll(buttonDel, buttonRec);
        botones.setAlignment(Pos.CENTER);
        Button buttonMenu = new Button("Volver al menú");
        buttonMenu.setOnAction(e -> window.setScene(menu));
        HBox menuDel = new HBox(50);
        menuDel.getChildren().addAll(paneDel, paneDel2);
        menuDel.setAlignment(Pos.CENTER);
        VBox delVert = new VBox(20);
        delVert.getChildren().addAll(titulo, menuDel, botones, buttonMenu);
        delVert.setAlignment(Pos.CENTER);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        del = new Scene(delVert, 1100, 800);
        del.getStylesheets().add(css);
        return del;
    }
    
    public Scene menuOnePlayer(Scene menu, Stage window, double width, double height) {
        Scene one;
        Trivial t = new Trivial();
        Label titulo = new Label("TRIVIAL: UN JUGADOR");
        titulo.setId("titulo");
        int opcion = 1;
        Label labGame = new Label("Jugador 1");
        Button buttonCancel = new Button("Terminar juego");
        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
                t.reset();
                Library.getAlert("Valores restaurados a su valor inicial");
            }
        });
        VBox game = new VBox(20);
        game.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent arg0) {
             if (Trivial.getNumero(opcion) == 10) {
                 game.setDisable(true);
                 Library.getAlert("HAS GANADOOOOOOOOO, ENHORABUENA");
             }
             
          }
        });
        game.setOnMouseMoved(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent arg0) {
             if (Trivial.getNumero(opcion) == 10) {
                 game.setDisable(true);
                 Library.getAlert("HAS GANADOOOOOOOOO, ENHORABUENA");
             }
          }
        });
        game.getChildren().addAll(labGame, player(opcion));
        Button buttonMenu = new Button("Volver al menú");
        buttonMenu.setOnAction(e -> window.setScene(menu));
        VBox onePlayer = new VBox(20);
        onePlayer.getChildren().addAll(titulo, game, buttonCancel, buttonMenu);
        onePlayer.setAlignment(Pos.CENTER);
        one = new Scene(onePlayer, 800, 800);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        one.getStylesheets().add(css);
        return one;
    }
    
    public Scene twoPlayers (Scene menu, Stage window, double width, double height) {
        Scene two;
        Trivial t = new Trivial();
        Label labGame = new Label("Jugador 1");
        int opcion = 1;
        VBox game = new VBox(20);
        Label labGameTwo = new Label("Jugador 2");
        int opcionTwo = 2;
        VBox gameTwo = new VBox(20);
        Button buttonCancel = new Button("Terminar juego");
        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
                t.reset();
                Library.getAlert("Valores restaurados a su valor inicial");
            }
        });
        game.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent arg0) {
                String jugador = "";
                if (Trivial.getNumero(opcion) == 10 || Trivial.getNumero(opcionTwo) == 10) {
                    if (Trivial.getNumero(opcion) == 10) jugador = "Jugador 1";
                    if (Trivial.getNumero(opcionTwo) == 10) jugador = "Jugador 2";
                    game.setDisable(true);
                    gameTwo.setDisable(true);
                    Library.getAlert("HAS GANADOOOOOOOOO " + jugador + ", ENHORABUENA");
                }
          }
        });
        gameTwo.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent arg0) {
                String jugador = "";
                if (Trivial.getNumero(opcion) == 10 || Trivial.getNumero(opcionTwo) == 10) {
                    if (Trivial.getNumero(opcion) == 10) jugador = "Jugador 1";
                    if (Trivial.getNumero(opcionTwo) == 10) jugador = "Jugador 2";
                    game.setDisable(true);
                    gameTwo.setDisable(true);
                    Library.getAlert("HAS GANADOOOOOOOOO " + jugador + ", ENHORABUENA");
                }
          }
        });
        game.getChildren().addAll(labGame, player(opcion));
        gameTwo.getChildren().addAll(labGameTwo, player(opcionTwo));
        Button buttonMenu = new Button("Volver al menú");
        buttonMenu.setOnAction(e -> window.setScene(menu));
        VBox twoPlayer = new VBox(20);
        twoPlayer.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent arg0) {
             if (Trivial.isTurno()) {
                 game.setDisable(false);
                 gameTwo.setDisable(true);
             }
             else {
                 game.setDisable(true);
                 gameTwo.setDisable(false);
             }
          }
        });
        twoPlayer.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (Trivial.isTurno()) {
                    game.setDisable(false);
                    gameTwo.setDisable(true);
                }
                else {
                    game.setDisable(true);
                    gameTwo.setDisable(false);
                }
            }
        });
        GridPane paneGame = new GridPane();
        paneGame.add(game,0,0);
        ColumnConstraints col1 = new ColumnConstraints(400,550,550);
        paneGame.getColumnConstraints().add(0, col1);
        GridPane paneGame2 = new GridPane();
        paneGame2 .add(gameTwo,0,0);
        paneGame2 .getColumnConstraints().add(0, col1);
        HBox twohori = new HBox(20);
        twohori.getChildren().addAll(paneGame, paneGame2);
        twoPlayer.getChildren().addAll(twohori, buttonCancel, buttonMenu);
        two = new Scene(twoPlayer, 1100, 800);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        two.getStylesheets().add(css);
        return two;
    }
    
    public static VBox add(int opcion) {
        Label cat = new Label("Categoría");
        Label vacio = new Label("");
        ChoiceBox cbCat = new ChoiceBox(FXCollections.observableArrayList(
        "Ciencia y Naturaleza", "Geografía", "Arte y Literatura", "Espectáculos", "Deportes", "Historia"));
        cbCat.setTooltip(new Tooltip("Elige la categoría"));
        cbCat.getSelectionModel().selectFirst();
        HBox category = new HBox(20);
        category.getChildren().addAll(cat, cbCat);
        category.setAlignment(Pos.CENTER);
        Label type = new Label("Tipo de pregunta");
        ChoiceBox cbType = new ChoiceBox(FXCollections.observableArrayList(
        "Simple", "Compuesta", "Si o No"));
        cbCat.setTooltip(new Tooltip("Elige el tipo"));
        cbType.getSelectionModel().selectFirst();
        HBox boxType = new HBox(20);
        boxType.getChildren().addAll(type, cbType);
        boxType.setAlignment(Pos.CENTER);
        Label labelP = new Label("Escribe la pregunta que deseas introducir");
        TextField textP = new TextField();
        textP.setMaxWidth(350);
        Label labelR = new Label("Escribe la respuesta que deseas introducir");
        TextField textR = new TextField();
        textR.setMaxWidth(350);
        Label labelId = new Label("Escribe el ID de la pregunta que quieras modificar");
        TextField textId = new TextField();
        textId.setMaxWidth(40);
        Button buttonSend = new Button("Enviar la pregunta");
        buttonSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String s = textP.getText();
                String r = textR.getText();
                String cat = (String) cbCat.getValue();
                String t = (String)cbType.getValue();
                int numCat = Library.convertCateg(cat);
                int numT = Library.convertType(t);
                if (s.equals("") || r.equals("")) Library.getAlert("Uno de los campos está vacio");
                else if (s.length() > 100 ) Library.getAlert("Pregunta demasiado larga");
                else if ((numT == 1 && r.length() > 30) 
                        || (numT == 2 && r.length() > 80) 
                        || (numT == 3 && r.length() > 2)) Library.getAlert("Respuesta demasiado larga");
                else {
                    if (opcion == 1) {
                    Trivial.add(numCat, numT, s, r);
                    }
                    if (opcion == 2) {
                        File f = new File("Preguntas.dat");
                        int numId = Integer.parseInt(textId.getText());
                        if (numId > 0 && numId <= Library.getId(f, Preguntas.getSpace())) {
                            String id = Trivial.lookFor(numId);
                            String[] preg = id.split("-");
                            if (preg[1].equals(t)) Trivial.toModify(numId, numCat, numT, s, r);
                            else if(textId.getText().isEmpty()) Library.getAlert("No has metido ningún ID");
                            else Library.getAlert("No puedes cambiar el tipo de pregunta");
                        }
                        else {
                            Library.getAlert("Esa pregunta con ese ID no existe");
                        }
                       
                        textId.clear();
                    } 
                }
                textP.clear();
                textR.clear();
            }
        });
        VBox boxAdd = new VBox(20);
        boxAdd.setAlignment(Pos.CENTER);
        boxAdd.setPrefWidth(400);
        boxAdd.setSpacing(25);
        boxAdd.setPadding(new Insets(0, 20, 10, 20));
        boxAdd.setId("cajaAñadir");
        buttonSend.setMaxWidth(boxAdd.getPrefWidth());
        if (opcion == 1) boxAdd.getChildren().addAll(category, boxType, labelP, textP, labelR, textR, buttonSend);
        if (opcion == 2) boxAdd.getChildren().addAll(vacio, category, boxType, labelP, textP, labelR, textR, labelId, textId, buttonSend);
        return boxAdd;
    }
    
    public static VBox search() {
        File f = new File("Preguntas.dat");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
        Label label = new Label("Elige el número de pregunta que quieras buscar");
        TextField numPreguntas = new TextField("Hay " + Library.getId(f, Preguntas.getSpace())+ " preguntas");
        numPreguntas.setEditable(false);
        numPreguntas.setMaxWidth(150);
        Label selectPreg = new Label("Introduce ID");
        TextField pregunta = new TextField();
        pregunta.setMaxWidth(50);
        Label lInfo = new Label("Información");
        lInfo.setId("info");
        Label lCat = new Label("Categoría");
        Label lType = new Label("Tipo");
        Label lPreg = new Label("Pregunta");
        Label lResp = new Label("Respuesta");
        TextField infoCat = new TextField();
        infoCat.setEditable(false);
        TextField infoType = new TextField();
        infoType.setEditable(false);
        TextField infoPreg = new TextField();
        infoPreg.setEditable(false);
        TextField infoResp = new TextField();
        infoResp.setEditable(false);
        infoCat.setMaxWidth(150);
        infoType.setMaxWidth(70);
        Button buttonSend = new Button("Mostrar");
        buttonSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String s = "";
                String[] preg;
                infoPreg.clear();
                infoResp.clear();
                int n = 0;
                if (pregunta.getText().isEmpty()) Library.getAlert("Error al introducir datos");
                else  n = Integer.parseInt(pregunta.getText());
                if (n > 0 && n <= Library.getId(f, Preguntas.getSpace())) {
                    s = Trivial.lookFor(n);
                    preg = s.split("-");
                    infoCat.setText(preg[0]);
                    infoType.setText(preg[1]);
                    infoPreg.setText(preg[2]);
                    infoPreg.setText(preg[3]);
                    infoResp.setText(preg[4]);
                }
                else Library.getAlert("Error al introducir datos");
                
            }
        });
        //label en columna 0-1-2-3 y fila 1
        grid.add(label, 0, 1,4,1 );
        //numPreguntas en columna 1-2-3 y fila 2
        grid.add(numPreguntas, 1, 2,3,1);
        //columna 0 y fila 3
        grid.add(selectPreg, 0, 3);
        //columna 1 y fila 3 
        grid.add(pregunta, 1, 3);
        //columna 1-2 y fila 4
        grid.add(lInfo, 1,4,2,1);
        //columna 0 y fila 5
        grid.add(lCat, 0, 5);
        //columna 1-2-3 y fila 5
        grid.add(infoCat, 1, 5,3,1);
        //columna 0 y fila 6
        grid.add(lType, 0, 6);
        //columna 1-2-3 y fila 6
        grid.add(infoType, 1, 6,3,1);
        //columna 1-2-3 y fila 7
        grid.add(lPreg, 1, 7, 3, 1);
        //col 0-1-2-3, fila 8
        grid.add(infoPreg, 0, 8,4,1);
        grid.add(lResp, 1, 9,3,1);
        grid.add(infoResp, 0,11,4,1);
        grid.add(buttonSend, 1,12,3,1);
        ColumnConstraints col1 = new ColumnConstraints(100, 200,350);
        ColumnConstraints col2 = new ColumnConstraints(100, 100,175);
        ColumnConstraints colEmpty = new ColumnConstraints();
        grid.getColumnConstraints().add(0, colEmpty);
        grid.getColumnConstraints().add(1, col1);
        grid.getColumnConstraints().add(2, col2);
        grid.setId("caja2");
        //grid.setGridLinesVisible(true);
        VBox menuLK = new VBox(20);
        menuLK.setId("menu");
        menuLK.setAlignment(Pos.CENTER);
        menuLK.getChildren().addAll(grid);
        return menuLK;
    }
    
    public VBox player(int opcion) {
        Trivial t = new Trivial();
        ObservableList<String> listChoice = FXCollections.observableArrayList();
        File f = new File("Preguntas.dat");
        Label labelPregun = new Label("Pregunta");
        TextField textPreg = new TextField();
        ChoiceBox cbAnswer = new ChoiceBox(listChoice);
        cbAnswer.setVisible(false);
        TextField textCorrecta = new TextField();
        Label lescribir = new Label("Respuesta");
        TextField textEscribir = new TextField();
        textEscribir.setVisible(false);
        ToggleGroup group = new ToggleGroup();
        RadioButton rbYes = new RadioButton("Si");
        RadioButton rbNo = new RadioButton("No");
        rbYes.setToggleGroup(group);
        rbNo.setToggleGroup(group);
        HBox radioAnswer = new HBox(20);
        radioAnswer.getChildren().addAll(rbYes, rbNo);
        radioAnswer.setVisible(false);
        TextField contador = new TextField();
        Button buttonSend = new Button("Empezar juego");
        VBox menuOne = new VBox(20);
        buttonSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (!Trivial.isResponder(opcion)) {
                    String s = "";
                    if (textEscribir.isVisible()) s = textEscribir.getText();
                    else if (cbAnswer.isVisible()) s = (String) cbAnswer.getValue();
                    else {
                        if (rbYes.isSelected()) s = "Si";
                        else if (rbNo.isSelected()) s = "No";
                        else s = "";
                    }
                    s = Library.ridAccent(s).toUpperCase();
                    String answer = textCorrecta.getText();
                    answer = Library.ridAccent(answer).toUpperCase();
                    if (s.contains(answer)) {
                        Library.getAlert("Respuesta correcta");
                        Trivial.setNumero((Trivial.getNumero(opcion)+1), opcion);
                        Trivial.setResponder(true, opcion);
                        if (Trivial.isTurno()) Trivial.setTurno(false);
                        else Trivial.setTurno(true);
                    }
                    else if (s.equals("") || s.equals(" ")) {
                        Library.getAlert("No has respondido la pregunta");
                    }
                    else {
                        Library.getAlert("Respuesta incorrecta");
                        Trivial.setResponder(true, opcion);
                        if (Trivial.isTurno()) Trivial.setTurno(false);
                        else Trivial.setTurno(true);
                    }
                    if (Trivial.isResponder(opcion)) {
                        contador.setText("" + Trivial.getNumero(opcion));
                        textPreg.clear(); // Para borrar el campo una vez enviado
                        textCorrecta.clear();
                        textEscribir.clear();
                        
                    }
                }
                if (Trivial.isResponder(opcion)) {
                    buttonSend.setText("Enviar pregunta");
                    Trivial.setDelText(false);
                    String preResp = t.getPregunta(Library.getId(f, Preguntas.getSpace()));
                    String[] question = preResp.split("-");
                    textPreg.setText(question[0]);
                    textPreg.setEditable(false);
                    int n = Library.convertType(question[0]);
                    if (n == 1) {
                        cbAnswer.setVisible(false);
                        radioAnswer.setVisible(false);
                        textEscribir.setVisible(true);
                        textCorrecta.setText(question[1]);
                    }
                    if (n == 2) {
                        textEscribir.setVisible(false);
                        radioAnswer.setVisible(false);
                        listChoice.removeAll();
                        cbAnswer.getItems().clear();
                        String[] answer = question[1].split("_");
                        listChoice.addAll(answer[0], answer[1], answer[2]);
                        cbAnswer.setItems(listChoice);
                        cbAnswer.setVisible(true);
                        textCorrecta.setText(answer[3]);
                    }
                    if (n == 3) {
                        cbAnswer.setVisible(false);
                        textEscribir.setVisible(false);
                        rbYes.setSelected(false);
                        rbNo.setSelected(false);
                        radioAnswer.setVisible(true);
                        textCorrecta.setText(question[1]);
                    }
                    Trivial.setNumPreg(Trivial.getNumPreg()+1);
                    Trivial.setResponder(false, opcion);
                }
            }
        });
        menuOne.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent arg0) {
             if (Trivial.isDelText()) {
                 textPreg.clear();
                 textCorrecta.clear();
                 textEscribir.clear();
                 contador.clear();
                 buttonSend.setText("Empezar");
             }
          }
        });
        menuOne.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (Trivial.isDelText()) {
                 textPreg.clear();
                 textCorrecta.clear();
                 textEscribir.clear();
                 contador.clear();
                 buttonSend.setText("Empezar");
             }
            }
        });
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
        grid.add(labelPregun, 0,0);
        grid.add(textPreg,0,1);
        grid.add(lescribir,0,2);
        grid.add(textEscribir,0,3);
        grid.add(cbAnswer,1,3);
        grid.add(radioAnswer,2,3);
        grid.add(contador,0,4);
        menuOne.getChildren().addAll(grid, buttonSend);
        menuOne.setAlignment(Pos.CENTER);
        return menuOne;
    }
    
    private final static ObservableList<Tabla> tablaData = FXCollections.observableArrayList();
    public static ObservableList<Tabla> getTablaData() {
        return tablaData;
    }
    
    public static void addTabla(int id, String cat, String type, String question, String answer, boolean del) {
        tablaData.add(new Tabla(id, cat, type, question, answer, del));
    }
}
