/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivial;

/*import static Trivial.Interface.numero;
import static Trivial.Interface.responder;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author DAW13
 */
/*public class prueba {
    String dosjuga = "Empieza el juego\n"
                + "Dos jugadores, un ganador, duelo a muerte...";
    
    
    public static Scene menuOnePlayer(Scene menu, Stage window) {
        Scene one;
        Trivial t = new Trivial();
        
        File f = new File("Preguntas.dat");
        Label label = new Label("Empieza el juego");
        TextField textPreg = new TextField();
        Label respuestas = new Label("Posibles respuestas");
        TextField textRespuestas = new TextField();
        TextField textCorrecta = new TextField();
        Button buttonGen = new Button("Generar pregunta");
        buttonGen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (responder) {
                    String preResp = t.getPregunta(Library.getId(f, Preguntas.getSpace()));
                    String[] question = preResp.split("-");
                    textPreg.setText(question[0]);
                    textPreg.setDisable(true);
                    int n = Library.convertType(question[0]);
                    if (n == 2) {
                        String[] answer = question[1].split("_");
                        textRespuestas.setText(answer[0] + " " + answer[1] + " " + answer[2]);
                        textCorrecta.setText(answer[3]);
                    }
                    else textCorrecta.setText(question[1]);
                    responder = false;
                }
                else Library.getAlert("Tienes que responder la pregunta");
            }
        });
        Label lescribir = new Label("Escribe aquí la respuesta correcta");
        TextField textEscribir = new TextField();
        TextField contador = new TextField();
        Button buttonSend = new Button("Enviar respuesta");
        buttonSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                String s = textEscribir.getText();
                s = Library.ridAccent(s).toUpperCase();
                String answer = textCorrecta.getText();
                answer = Library.ridAccent(answer).toUpperCase();
                if (answer.contains(s)) {
                    Library.getAlert("Respuesta correcta");
                    numero++;
                }
                else if (textPreg.getText().equals("")) {
                    Library.getAlert("No has pedido ninguna pregunta, para jugar"
                            + "tienes que pedir preguntas");
                }
                else {
                    Library.getAlert("Respuesta incorrecta");
                }
                contador.setText("" + numero);
                responder = true;
                textPreg.clear(); // Para borrar el campo una vez enviado
                textRespuestas.clear();
                textCorrecta.clear();
                textEscribir.clear();
            }
        });
        Button buttonMenu = new Button("Volver al menú");
        buttonMenu.setOnAction(e -> window.setScene(menu));
        VBox menuOne = new VBox(20);
        VBox menuStart = new VBox(20);
        menuOne.getChildren().addAll(label, textPreg, respuestas, textRespuestas, 
                buttonGen, lescribir, textEscribir, contador, buttonSend, buttonMenu, menuStart);
        
        one = new Scene(menuOne, 600,600);
        return one;
    }
    
}*/
