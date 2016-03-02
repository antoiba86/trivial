/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivial;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Anto
 */
public class Menu extends Application {
    Stage window;
    Scene menu,add,show,lookFor, modify,delete,randomly,dual;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Interface inter = new Interface();
        window = primaryStage;
        //Conseguimos saber los valores de la ventana donde se ejecuta
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        //Conseguir el ancho de la pantalla donde se ejecute
        double width = visualBounds.getWidth();
        //Altura de la pantalla
        double height = visualBounds.getHeight();
        
        //Menu principal
        Label menuLabel = new Label("Bienvenidos al trivial v.1");
        menuLabel.setId("menulabel");
        menuLabel.getStyleClass().add("menulabel");
        Button buttonAdd = new Button("Añadir una pregunta y su respuesta");
        buttonAdd.setOnAction(e -> window.setScene(add));
        
        Button buttonShow = new Button("Enseñar preguntas y respuestas");
        buttonShow.setOnAction(e -> window.setScene(show));
        Button buttonLK = new Button("Buscar una pregunta y su respuesta");
        buttonLK.setOnAction(e -> window.setScene(lookFor));
        Button buttonModify = new Button("Modificar preguntas");
        buttonModify.setOnAction(e -> window.setScene(modify));
        Button buttonDelete = new Button("Borrar una pregunta");
        buttonDelete.setOnAction(e -> window.setScene(delete));
        Button buttonRandom = new Button("Modalidad un jugador");
        buttonRandom.setOnAction(e -> window.setScene(randomly));
        Button buttonDual = new Button("Ronda dos jugadores");
        buttonDual.setOnAction(e -> window.setScene(dual));
        Label copyrigth= new Label ("Creado por Antonio J. Ibáñez");
        
        
        VBox menuIni = new VBox(20);
        //Para que todos los botones tenga la misma medida
        menuIni.setPrefWidth(300);
        menuIni.setSpacing(10);
        menuIni.setPadding(new Insets(0, 20, 10, 20)); 
        buttonAdd.setMaxWidth(menuIni.getPrefWidth());
        buttonShow.setMaxWidth(menuIni.getPrefWidth());
        buttonLK.setMaxWidth(menuIni.getPrefWidth());
        buttonModify.setMaxWidth(menuIni.getPrefWidth());
        buttonDelete.setMaxWidth(menuIni.getPrefWidth());
        buttonRandom.setMaxWidth(menuIni.getPrefWidth());
        buttonDual.setMaxWidth(menuIni.getPrefWidth());
        menuIni.getChildren().addAll(menuLabel,buttonAdd,buttonShow, buttonLK, 
                buttonModify,buttonDelete,buttonRandom,buttonDual, copyrigth);
        menuIni.setAlignment(Pos.CENTER);
        
        //Mirar esta pagina http://stackoverflow.com/questions/25754524/javafx-buttons-with-same-size

        

        /*btn1.setMinWidth(vBox.getPrefWidth());
        btn2.setMinWidth(vBox.getPrefWidth());

        vBox.getChildren().addAll(btn1, btn2);*/
        menu = new Scene (menuIni, 1000, 800);
        String css = Menu.class.getResource("menu.css").toExternalForm();
        menu.getStylesheets().add(css);
        
        add = Interface.menuAdd(menu, window, width, height);
        show = Interface.menuShow(menu, window, width, height);
        lookFor = Interface.menuLookFor(menu, window, width, height);
        modify = Interface.menuModify(menu, window, width, height);
        delete = Interface.menuDel(menu, window, width, height);
        randomly = inter.menuOnePlayer(menu,window, width, height);
        dual = inter.twoPlayers(menu, window, width, height);
        
        window.setScene(menu);
        window.setTitle("Trivial");
        window.show();
        
    }
}
