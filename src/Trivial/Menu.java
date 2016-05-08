/**
 * <h1>TRIVIADUNO</h1>
 * <p>Program created to imitate the popular game
 * Trivial</p>
 */
package Trivial;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Main class of the game
 * @author AntoIba86
 * @version 1.00 
 */
public class Menu extends Application {
    Stage window;
    Scene menu,add,show,lookFor, modify,delete,randomly,dual, instructions;
    
    /**
     * Represents the main menu of the game
     * @param args launch
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Method to represent the principal scene of the game
     * @param primaryStage It is the window of the game
     */
    @Override
    public void start(Stage primaryStage) {
        Interface inter = new Interface();
        primaryStage.setResizable(false);
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
        buttonShow.setOnAction((ActionEvent e) -> {
            window.setResizable(true);
            window.setScene(show);
        });
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
        Button buttonIntr = new Button("Instrucciones");
        buttonIntr.setOnAction(e -> window.setScene(instructions));
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
        buttonIntr.setMaxWidth(menuIni.getPrefWidth());
        menuIni.getChildren().addAll(menuLabel,buttonAdd,buttonShow, buttonLK,
                buttonModify,buttonDelete,buttonRandom,buttonDual,buttonIntr, copyrigth);
        menuIni.setAlignment(Pos.CENTER);
        menu = new Scene (menuIni, 1000, 600);
        String css = Menu.class.getResource("menu.css").toExternalForm();
        menu.getStylesheets().add(css);
        add = Interface.menuAdd(menu, window);
        show = Interface.menuShow(menu, window, width);
        lookFor = Interface.menuLookFor(menu, window);
        modify = Interface.menuModify(menu, window);
        delete = Interface.menuDel(menu, window);
        randomly = inter.menuOnePlayer(menu,window);
        dual = inter.twoPlayers(menu, window);
        instructions = Interface.instructions(menu, window);
        window.setScene(menu);
        window.setTitle("Trivial");
        window.show();
    }
}
