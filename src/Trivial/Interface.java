package Trivial;

import java.io.*;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * Class with all the method of the scene of the game
 * @author AntoIba86
 */
public class Interface {
    private final static ObservableList<Tabla> tablaData = FXCollections.observableArrayList();
    /**
     * Method to get the data for the table in menuShow
     * @return The ObservableList for the table
     */
    public static ObservableList<Tabla> getTablaData() {
        return tablaData;
    }
    
    /**
     * Add one element on the table of the scene Show
     * @param id It is the id of the question
     * @param cat It is the category of the question
     * @param type It is the type of the question
     * @param question It is the question of the question
     * @param answer It is the answer of the question
     * @param del It is the state of the question
     */
    public static void addTabla(int id, String cat, String type, String question, String answer, boolean del) {
        tablaData.add(new Tabla(id, cat, type, question, answer, del));
    }
    /**
     * This scene is the menu to add a question into the file
     * @param menu It is the principal scene with the other options
     * @param window It is the principal stage to navigate
     * @return The menu Add for the principal stage
     */
    public static Scene menuAdd(Scene menu, Stage window) {
        //Menu añadir
        Scene add;
        Label titulo = new Label("Menu añadir");
        titulo.setId("titulo");
        Button buttonExit = new Button("Volver al menú");
        buttonExit.setOnAction(e -> window.setScene(menu));
        VBox boxAdd = new VBox(20);
        boxAdd.getChildren().addAll(titulo, add(1), buttonExit);
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
    
    /**
     * This scene is the menu to show all question from the file
     * @param menu It is the principal scene with the other options
     * @param window It is the principal stage to navigate
     * @param width It is the width of the screen
     * @return The scene Show for the principal stage
     */
    public static Scene menuShow (Scene menu, Stage window, double width) {
        Scene show;
        Label titulo = new Label("Mostrar Preguntas");
        titulo.setId("titulo");
        TableView<Tabla> table = new TableView();
        //Resize the table for the window
        //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn thId = new TableColumn("Id");
        thId.setCellValueFactory(new PropertyValueFactory<>("id"));
        thId.prefWidthProperty().bind(table.widthProperty().multiply(0.03125));
        TableColumn thType = new TableColumn("Tipo");
        thType.prefWidthProperty().bind(table.widthProperty().multiply(0.0625));
        thType.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn thCat = new TableColumn("Categoria");
        thCat.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
        thCat.setCellValueFactory(new PropertyValueFactory<>("cat"));
        TableColumn thQuestion = new TableColumn("Pregunta");
        thQuestion.prefWidthProperty().bind(table.widthProperty().multiply(0.40625));
        thQuestion.setCellValueFactory(new PropertyValueFactory<>("question"));
        TableColumn thAnswer = new TableColumn("Respuesta");
        thAnswer.prefWidthProperty().bind(table.widthProperty().multiply(0.3125)); // w * 1/4
        thAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        TableColumn thDel = new TableColumn("Borrado");
        thDel.prefWidthProperty().bind(table.widthProperty().multiply(0.0625));
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
        Button buttonExit = new Button("Volver al menú");
        buttonExit.setOnAction((ActionEvent e) -> {
            tablaData.removeAll(getTablaData());
            window.setResizable(false);
            window.setScene(menu);
        });
        VBox menuShow = new VBox(20);
        menuShow.setSpacing(5);
        menuShow.setPadding(new Insets(10, 0, 0, 10));
        menuShow.getChildren().addAll(titulo, table, buttonSend, buttonExit);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        show = new Scene(menuShow, width-200, 600);
        show.getStylesheets().add(css);
        return show;
    }
    
    /**
     * This scene is the menu to show a question from the file
     * @param menu It is the principal scene with the other options
     * @param window It is the principal stage to navigate
     * @return The menu Look for the principal stage
     */
    public static Scene menuLookFor (Scene menu, Stage window) {
        Scene lookFor;
        Label titulo = new Label("Buscar por ID");
        titulo.setId("titulo");
        Label espaciovacio = new Label("");
        Button buttonExit = new Button("Volver al menú");
        buttonExit.setOnAction(e -> window.setScene(menu));
        VBox menuLK = new VBox();
        menuLK.getChildren().addAll(titulo, search(), espaciovacio, buttonExit);
        menuLK.setAlignment(Pos.CENTER);
        menuLK.setPrefWidth(300);
        menuLK.setMaxWidth(700);
        menuLK.setMaxHeight(700);
        lookFor = new Scene(menuLK, 800, 800);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        lookFor.getStylesheets().add(css);
        return lookFor;
    }
    
    /**
     * This scene is the menu to modify a question into the file
     * @param menu It is the principal scene with the other options
     * @param window It is the principal stage to navigate
     * @return The menu Modify for the principal stage
     */
    public static Scene menuModify(Scene menu, Stage window) {
        Scene modify;
        Label titulo = new Label("Menú Modificar");
        titulo.setId("titulo");
        Button buttonExit = new Button("Volver al menú");
        buttonExit.setOnAction(e -> window.setScene(menu));
        GridPane paneModify = new GridPane();
        paneModify.add(add(2),0,0);
        ColumnConstraints col1 = new ColumnConstraints(400,550,550);
        paneModify.getColumnConstraints().add(0, col1);
        GridPane paneModify2 = new GridPane();
        paneModify2.add(search(),0,0);
        paneModify2.getColumnConstraints().add(0, col1);
        paneModify.setId("cajaMod");
        HBox menuMod = new HBox(50);
        menuMod.getChildren().addAll(paneModify2, paneModify);
        menuMod.setAlignment(Pos.CENTER);
        VBox menuShow = new VBox(20);
        menuShow.getChildren().addAll(titulo,menuMod, buttonExit);
        menuShow.setAlignment(Pos.CENTER);
        modify = new Scene(menuShow, 1100, 800);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        modify.getStylesheets().add(css);
        return modify;
    }
    
    /**
     * This scene is the menu to delete a question into the file
     * @param menu It is the principal scene with the other options
     * @param window It is the principal stage to navigate
     * @return The menu Delete for the principal stage
     */
    public static Scene menuDel(Scene menu, Stage window) {
        Scene del;
        File f = new File("Preguntas.dat");
        Label titulo = new Label("Menú Borrar");
        titulo.setId("titulo");
        Label labelDel = new Label("Introduce el ID de la pregunta "
                + "para borrar o recuperar.");
        TextField enterId = new TextField();
        enterId.setMaxWidth(100);
        Button buttonDel = new Button("Borrar");
        buttonDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int id = 0;
                if (enterId.getText().isEmpty()) id = 0;
                else if (enterId.getText().matches("\\d*")) id = Integer.parseInt(enterId.getText());
                else id = 0;
                if (id > 0 && id <= Library.getId(f, Preguntas.getSpace())) Trivial.toDelete(id, 1);
                else Library.getAlertInfo("Ese ID no es computable");
                enterId.clear(); // Para borrar el campo una vez enviado
            }
        });
        Button buttonRecover = new Button("Recuperar");
        buttonRecover.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int id = 0;
                if (enterId.getText().isEmpty()) id = 0;
                else if (enterId.getText().matches("\\d*")) id = Integer.parseInt(enterId.getText());
                else id = 0;
                if (id > 0 && id <= Library.getId(f, Preguntas.getSpace())) Trivial.toDelete(id, 2);
                else Library.getAlertInfo("Esa pregunta con ese ID no existe");
                enterId.clear(); // Para borrar el campo una vez enviado
            }
        });
        HBox buttons = new HBox(20);
        GridPane paneDel = new GridPane();
        paneDel.add(search(),0,0);
        ColumnConstraints col1 = new ColumnConstraints(400,550,550);
        paneDel.getColumnConstraints().add(0, col1);
        GridPane paneDel2 = new GridPane();
        paneDel2.setHgap(10);
        paneDel2.setVgap(12);
        paneDel2.add(labelDel,0,0);
        paneDel2.add(enterId, 0, 1);
        paneDel2.getColumnConstraints().add(0, col1);
        paneDel2.setMaxHeight(300);
        paneDel2.setId("cajaMod");
        paneDel2.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(buttonDel, buttonRecover);
        buttons.setAlignment(Pos.CENTER);
        Button buttonExit = new Button("Volver al menú");
        buttonExit.setOnAction(e -> window.setScene(menu));
        HBox menuDel = new HBox(50);
        menuDel.getChildren().addAll(paneDel, paneDel2);
        menuDel.setAlignment(Pos.CENTER);
        VBox menuVert = new VBox(20);
        menuVert.getChildren().addAll(titulo, menuDel, buttons, buttonExit);
        menuVert.setAlignment(Pos.CENTER);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        del = new Scene(menuVert, 1100, 800);
        del.getStylesheets().add(css);
        return del;
    }
    /**
     * Menu to show the instructions to play
     * @param menu It is the scene to return to the menu
     * @param window It is the window of the game
     * @return The scene instructions
     */
    public static Scene instructions (Scene menu, Stage window) {
        Scene instructions;
        VBox caja = new VBox(20);
        Text text = new Text("Las intrucciones son sencillas, para el modo Un Jugador"
                + " hay que responder 10 respuestas correctas para ganar.\n\n"
                + " Para el modo Dos Jugadores, el juego va por turnos, primero el Jugador 1"
                + " y luego el Jugador 2, al final del turno, quien primero llegue a 10 respuestas correctas"
                + " GANA.\n Si al final del turno se va empate a 10, se sigue jugando hasta que uno de los jugadores"
                + " falle una respuesta.");
        TextFlow intruc = new TextFlow(text);
        intruc.setId("Text");
        Button buttonExit = new Button("Volver al menú");
        buttonExit.setOnAction(e -> window.setScene(menu));
        caja.getChildren().addAll(intruc, buttonExit);
        caja.setAlignment(Pos.CENTER);
        instructions = new Scene(caja, 600, 300);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        instructions.getStylesheets().add(css);
        return instructions;
    }
    
    /**
     * This scene is the menu to play the game with one player
     * @param menu It is the principal scene with the other options
     * @param window It is the principal stage to navigate
     * @return The menu One Player for the principal stage
     */
    public Scene menuOnePlayer(Scene menu, Stage window) {
        Scene one;
        Trivial t = new Trivial();
        Label titulo = new Label("TRIVIADUNO");
        titulo.setId("tituloUno");
        int option = 1;
        Label namePlayer = new Label("Jugador 1");
        VBox game = new VBox(20);
        Button buttonCancel = new Button("Terminar juego");
        //Para resetear los valores
        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
                //Resetea los valores del juego
                boolean confirm;
                confirm = Library.getAlertConf("¿Quieres empezar de nuevo?");
                if (confirm) {
                    t.reset();
                    game.setDisable(false);
                    Library.getAlertInfo("Valores restaurados a su valor inicial");
                }
            }
        });
        //Para cuando se gana
        game.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent arg0) {
             if (Trivial.getNumero(option) == 10) {
                 game.setDisable(true);
                 Library.musicWinner();
                 Library.getAlertInfo("HAS GANADOOOOOOOOO, ENHORABUENA");
             }
          }
        });
        //Para cuando se gana
        game.setOnMouseMoved(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent arg0) {
             if (Trivial.getNumero(option) == 10) {
                 game.setDisable(true);
                 Library.musicWinner();
                 Library.getAlertInfo("HAS GANADOOOOOOOOO, ENHORABUENA");
             }
          }
        });
        //Para volver al menu
        game.getChildren().addAll(namePlayer, player(option));
        Button buttonExit = new Button("Volver al menú");
        buttonExit.setOnAction((ActionEvent e) -> {
            boolean confirm;
            confirm = Library.getAlertConf("¿Quieres volver al menú? Los datos se reiniciarán");
            if (confirm) {
                t.reset();
                game.setDisable(false);
                Library.getAlertInfo("Valores restaurados a su valor inicial");
                window.setScene(menu);
            }
        });
        VBox onePlayer = new VBox(20);
        onePlayer.getChildren().addAll(titulo, game, buttonCancel, buttonExit);
        onePlayer.setAlignment(Pos.CENTER);
        one = new Scene(onePlayer, 800, 800);
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        one.getStylesheets().add(css);
        return one;
    }
    
    /**
     * This scene is the menu to play the game with two players
     * @param menu It is the principal scene with the other options
     * @param window It is the principal stage to navigate
     * @return The menu Two Players for the principal stage
     */
    public Scene twoPlayers (Scene menu, Stage window) {
        Scene two;
        Trivial t = new Trivial();
        Label titulo = new Label("TRIVIADOS");
        titulo.setId("tituloDos");
        Label namePlayer = new Label("Jugador 1");
        int option = 1;
        VBox game = new VBox(20);
        Label namePlayerTwo = new Label("Jugador 2");
        int optionTwo = 2;
        VBox gameTwo = new VBox(20);
        VBox twoPlayer = new VBox(20);
        game.getChildren().addAll(namePlayer, player(option));
        gameTwo.getChildren().addAll(namePlayerTwo, player(optionTwo));
        Button buttonCancel = new Button("Terminar juego");
        buttonCancel.setOnAction((ActionEvent e) -> {
            boolean confirm;
            confirm = Library.getAlertConf("¿Quieres empezar de nuevo?");
            if (confirm) {
                t.reset();
                game.getChildren().clear();
                gameTwo.getChildren().clear();
                game.getChildren().addAll(namePlayer, player(option));
                gameTwo.getChildren().addAll(namePlayerTwo, player(optionTwo));
                Library.getAlertInfo("Valores restaurados a su valor inicial");
            }
            
        });
        Button buttonExit = new Button("Volver al menú");
        buttonExit.setOnAction((ActionEvent e) -> {
            boolean confirm;
            confirm = Library.getAlertConf("¿Quieres volver al menú? Los datos se reiniciarán");
            if (confirm) {
                t.reset();
                game.getChildren().clear();
                gameTwo.getChildren().clear();
                game.getChildren().addAll(namePlayer, player(option));
                gameTwo.getChildren().addAll(namePlayerTwo, player(optionTwo));
                Library.getAlertInfo("Valores restaurados a su valor inicial");
                window.setScene(menu);
            }
        });
        twoPlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                switch (Trivial.getTurno()) {
                    case 1:
                        game.setDisable(false);
                        gameTwo.setDisable(true);
                        break;
                    case 2:
                        game.setDisable(true);
                        gameTwo.setDisable(false);
                        break;
                }
            }
        });
        twoPlayer.setOnMouseMoved((MouseEvent arg0) -> {
            switch (Trivial.getTurno()) {
                case 1:
                    game.setDisable(false);
                    gameTwo.setDisable(true);
                    break;
                case 2:
                    game.setDisable(true);
                    gameTwo.setDisable(false);
                    break;
            }
        });
        GridPane paneGame = new GridPane();
        paneGame.add(game,0,0);
        ColumnConstraints col1 = new ColumnConstraints(400,550,550);
        paneGame.getColumnConstraints().add(0, col1);
        GridPane paneGame2 = new GridPane();
        paneGame2 .add(gameTwo,0,0);
        paneGame2 .getColumnConstraints().add(0, col1);
        HBox hboxTwoPlayer = new HBox(20);
        hboxTwoPlayer.getChildren().addAll(paneGame, paneGame2);
        twoPlayer.getChildren().addAll(titulo, hboxTwoPlayer, buttonCancel, buttonExit);
        twoPlayer.setAlignment(Pos.CENTER);
        two = new Scene(twoPlayer, 1100, 800);
        hboxTwoPlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                String jugador = "";
                if (Trivial.isWait()) {
                    if (Trivial.getNumero(option) > Trivial.getNumero(optionTwo)) jugador = "Jugador 1";
                    if (Trivial.getNumero(option) < Trivial.getNumero(optionTwo)) jugador = "Jugador 2";
                    game.setDisable(true);
                    gameTwo.setDisable(true);
                    Trivial.setTurno(3);
                    Library.musicWinner();
                    Trivial.setWait(false);
                    Library.getAlertInfo("HAS GANADOOOOOOOOO " + jugador + ", ENHORABUENA");
                    Trivial.setNumero(0,1);
                    Trivial.setNumero(0,2);
                }
            }
        });
        hboxTwoPlayer.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                String jugador = "";
                if (Trivial.isWait()) {
                    if (Trivial.getNumero(option) > Trivial.getNumero(optionTwo)) jugador = "Jugador 1";
                    if (Trivial.getNumero(option) < Trivial.getNumero(optionTwo)) jugador = "Jugador 2";
                    game.setDisable(true);
                    gameTwo.setDisable(true);
                    Trivial.setTurno(3);
                    Library.musicWinner();
                    Trivial.setWait(false);
                    Library.getAlertInfo("HAS GANADOOOOOOOOO " + jugador + ", ENHORABUENA");
                    Trivial.setNumero(0,1);
                    Trivial.setNumero(0,2);
                }
            }
        });
        String css = Menu.class.getResource("lookFor.css").toExternalForm();
        two.getStylesheets().add(css);
        return two;
    }
    
    /**
     * Menu add used to add or modify a question
     * @param option It it s the option to select the menu. With 1 the menu is used to add and with 2 is used the menu to modify
     * @return The menu Add for the menus add o modify
     */
    public static VBox add(int option) {
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
        Label labelQuestion = new Label("Escribe la pregunta que deseas introducir");
        TextField writeQuestion = new TextField();
        writeQuestion.setMaxWidth(350);
        Label labelAnswer = new Label("Escribe la respuesta que deseas introducir");
        TextField writeAnswer = new TextField();
        writeAnswer.setMaxWidth(350);
        Label labelId = new Label("Escribe el ID de la pregunta que quieras modificar");
        TextField textId = new TextField();
        textId.setMaxWidth(40);
        Button buttonSend = new Button("Enviar la pregunta");
        buttonSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //Recupero lo escrito de los textfield
                String s = writeQuestion.getText();
                String r = writeAnswer.getText();
                String cat = (String) cbCat.getValue();
                String t = (String)cbType.getValue();
                //La categoria y el tipo se pasan a Integer
                int numCat = Library.categToInt(cat);
                int numT = Library.typeToInt(t);
                if (s.equals("") || r.equals("")) Library.getAlertInfo("Uno de los campos está vacio");
                else if (s.length() > 100 ) Library.getAlertInfo("Pregunta demasiado larga");
                else if ((numT == 1 && r.length() > 30) 
                        || (numT == 2 && r.length() > 80) 
                        || (numT == 3 && r.length() > 2)) Library.getAlertInfo("Respuesta demasiado larga");
                else {
                    //Con option == 1 se crea una nueva pregunta
                    if (option == 1) {
                    Trivial.add(numCat, numT, s, r);
                    }
                    //Con option == 2 se modifica una existente
                    if (option == 2) {
                        File f = new File("Preguntas.dat");
                        int numId = 0;
                        if (textId.getText().isEmpty()) numId = 0;
                        else if (textId.getText().matches("\\d*")) numId = Integer.parseInt(textId.getText());
                        else numId = 0;
                        if (numId > 0 && numId <= Library.getId(f, Preguntas.getSpace())) {
                            String question = Trivial.search(numId);
                            String[] preg = question.split("-");
                            if (preg[1].equals(t)) Trivial.toModify(numId, numCat, numT, s, r);
                            else if(textId.getText().isEmpty()) Library.getAlertInfo("No has metido ningún ID");
                            else Library.getAlertInfo("No puedes cambiar el tipo de pregunta");
                        }
                        else {
                            Library.getAlertInfo("Ese número de ID no es computable");
                        }
                        numId = 0;
                        textId.clear();
                    } 
                }
                writeQuestion.clear();
                writeAnswer.clear();
            }
        });
        VBox boxAdd = new VBox(20);
        boxAdd.setAlignment(Pos.CENTER);
        boxAdd.setPrefWidth(400);
        boxAdd.setSpacing(25);
        boxAdd.setPadding(new Insets(0, 20, 10, 20));
        boxAdd.setId("cajaAñadir");
        buttonSend.setMaxWidth(boxAdd.getPrefWidth());
        if (option == 1) boxAdd.getChildren().addAll(category, boxType, labelQuestion, writeQuestion, labelAnswer, writeAnswer, buttonSend);
        if (option == 2) boxAdd.getChildren().addAll(vacio, category, boxType, labelQuestion, writeQuestion, labelAnswer, writeAnswer, labelId, textId, buttonSend);
        return boxAdd;
    }
    
    /**
     * The box that includes the searching of a question
     * @return The box that includes the menu to search a question
     */
    public static VBox search() {
        File f = new File("Preguntas.dat");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
        Label label = new Label("Elige el número de pregunta que quieras buscar");
        TextField numPreguntas = new TextField("Hay " + (Library.getId(f, Preguntas.getSpace())-1)+ " preguntas");
        numPreguntas.setEditable(false);
        numPreguntas.setMaxWidth(150);
        Label selectPreg = new Label("Introduce ID");
        TextField textId = new TextField();
        textId.setMaxWidth(50);
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
                String s;
                String[] preg;
                infoPreg.clear();
                infoResp.clear();
                int id = 0;
                if (textId.getText().isEmpty()) id = 0;
                else if (textId.getText().matches("\\d*")) id = Integer.parseInt(textId.getText());
                else id = 0;
                if (id > 0 && id <= Library.getId(f, Preguntas.getSpace())) {
                    s = Trivial.search(id);
                    preg = s.split("-");
                    infoCat.setText(preg[0]);
                    infoType.setText(preg[1]);
                    infoPreg.setText(preg[2]);
                    infoResp.setText(preg[4]);
                }
                else {
                    textId.clear();
                    Library.getAlertInfo("Error al introducir datos");
                }
                
            }
        });
        //label en columna 0-1-2-3 y fila 1
        grid.add(label, 0, 1,4,1 );
        //numPreguntas en columna 1-2-3 y fila 2
        grid.add(numPreguntas, 1, 2,3,1);
        //columna 0 y fila 3
        grid.add(selectPreg, 0, 3);
        //columna 1 y fila 3 
        grid.add(textId, 1, 3);
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
    
    /**
     * This box contains the information to play the game
     * @param option It is the option to decide which player is playing.
     * @return The box to play in the scene
     */
    public VBox player(int option) {
        Trivial t = new Trivial();
        File f = new File("Preguntas.dat");
        Label labelQuestion = new Label("Pregunta");
        TextField textQuestion = new TextField();
        ChoiceBox cbAnswer = new ChoiceBox();
        TextField answerCorrect = new TextField();
        Label labelAns = new Label("Respuesta");
        TextField writeAnswer = new TextField();
        ToggleGroup group = new ToggleGroup();
        RadioButton rbYes = new RadioButton("Si");
        RadioButton rbNo = new RadioButton("No");
        TextField score = new TextField();
        textQuestion.setEditable(false);
        textQuestion.setMinWidth(500);
        textQuestion.setMaxWidth(500);
        cbAnswer.setVisible(false);
        writeAnswer.setVisible(false);
        writeAnswer.setMaxWidth(300);
        rbYes.setToggleGroup(group);
        rbNo.setToggleGroup(group);
        HBox radioAnswer = new HBox(20);
        radioAnswer.getChildren().addAll(rbYes, rbNo);
        radioAnswer.setVisible(false);
        score.setEditable(false);
        score.setMaxWidth(50);
        score.setId("score");
        textQuestion.setId("question");
        writeAnswer.setId("answer");
        Button buttonSend = new Button("Empezar juego");
        VBox menuOne = new VBox(20);
        buttonSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ObservableList<String> list = FXCollections.observableArrayList();
                if (!Trivial.isResponder(option)) {
                    String s;
                    //Si es pregunta Simple, la respuesta se coge del textfield
                    if (writeAnswer.isVisible()) s = writeAnswer.getText();
                    //Si es compuesta de la choicebox
                    else if (cbAnswer.isVisible()) s = (String) cbAnswer.getValue();
                    //Si Si o No del radiobutton
                    else {
                        if (rbYes.isSelected()) s = "Si";
                        else if (rbNo.isSelected()) s = "No";
                        else s = "";
                    }
                    s = Library.ridAccent(s).toUpperCase();
                    String answer = answerCorrect.getText();
                    answer = Library.ridAccent(answer).toUpperCase();
                    if (s.contains(answer)) {
                        Library.getAlertInfo("Respuesta correcta");
                        Trivial.setNumero((Trivial.getNumero(option)+1), option);
                        Trivial.setResponder(true, option);
                        if (Trivial.getTurno() == 1) Trivial.setTurno(2);
                        else Trivial.setTurno(1);
                    }
                    else if (s.equals("") || s.equals(" ")) Library.getAlertInfo("No has respondido la pregunta");
                    else {
                        Library.getAlertInfo("Respuesta incorrecta");
                        Trivial.setResponder(true, option);
                        if (Trivial.getTurno() == 1) Trivial.setTurno(2);
                        else Trivial.setTurno(1);
                    }
                    if (Trivial.isResponder(option)) {
                        score.setText("" + Trivial.getNumero(option));
                        textQuestion.clear(); // Para borrar el campo una vez enviado
                        answerCorrect.clear();
                        writeAnswer.clear();
                    }
                }
                if (Trivial.isResponder(option)) {
                    buttonSend.setText("Enviar pregunta");
                    String preResp = t.getPregunta(Library.getId(f, Preguntas.getSpace()));
                    String[] question = preResp.split("-");
                    textQuestion.setStyle("-fx-background-color: " + Library.getColorText(Integer.parseInt(question[0])));
                    textQuestion.setText(question[2]);
                    int n = Integer.parseInt(question[1]);
                    cbAnswer.setVisible(false);
                    writeAnswer.setVisible(false);
                    radioAnswer.setVisible(false);
                    if (n == 1) {
                        writeAnswer.setVisible(true);
                        answerCorrect.setText(question[3]);
                    }
                    if (n == 2) {
                        list.removeAll();
                        cbAnswer.getItems().clear();
                        String[] answer = question[3].split("_");
                        list.addAll(answer[0], answer[1], answer[2]);
                        cbAnswer.setItems(list);
                        cbAnswer.setVisible(true);
                        cbAnswer.getSelectionModel().selectFirst();
                        answerCorrect.setText(answer[3]);
                    }
                    if (n == 3) {
                        rbYes.setSelected(false);
                        rbNo.setSelected(false);
                        radioAnswer.setVisible(true);
                        answerCorrect.setText(question[3]);
                    }
                    Trivial.setResponder(false, option);
                }
            }
        });
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
        grid.add(labelQuestion, 1,0);
        grid.add(textQuestion,0,1,3,1);
        grid.add(labelAns,1,2);
        grid.add(writeAnswer,0,3);
        grid.add(cbAnswer,1,3);
        grid.add(radioAnswer,2,3);
        grid.add(score,0,4);
        if (option == 1) grid.setId("cajaPlayer");
        if (option == 2) grid.setId("cajaPlayer2");
        String css = Menu.class.getResource("player.css").toExternalForm();
        menuOne.getStylesheets().add(css);
        menuOne.getChildren().addAll(grid, buttonSend);
        menuOne.setAlignment(Pos.CENTER);
        return menuOne;
    }
}
