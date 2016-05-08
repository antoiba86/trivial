package Trivial;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.Normalizer;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * Library of the game
 * @author AntoIba86
 */
public class Library {
    /**
     * Method to convert the category from String to Integer
     * @param s It is the category of the question
     * @return The category converted into Integer
     */
    public static int categToInt(String s) {
        int i = 0;
        if (s.contains("Ciencia")) i = 1;
        if (s.contains("Geogra")) i = 2;
        if (s.contains("Arte")) i = 3;
        if (s.contains("Espec")) i = 4;
        if (s.contains("Deportes")) i = 5;
        if (s.contains("Historia")) i = 6;
        return i;
    }
    /**
     * Method to convert the category from Integer to String
     * @param n It is the category of the question
     * @return The category converted into String
     */
    public static String categToString(int n) {
        String s = "";
        if (n == 1 ) s = "Ciencia y Naturaleza";
        if (n == 2) s = "Geografía";
        if (n == 3) s = "Arte y Literatura";
        if (n == 4) s = "Espectáculos";
        if (n == 5) s = "Deportes";
        if (n == 6) s = "Historia";
        
        return s;
    }
    
    /**
     * Method to convert the type from String to Integer
     * @param s It is the type of the question
     * @return The type converted into Integer
     */
    public static int typeToInt(String s) {
        int i = 0;
        if (s.contains("Simple")) i = 1;
        if (s.contains("Compuesta")) i = 2;
        if (s.contains("Si o No")) i = 3;
        return i;
    }
    /**
     * Method to convert the type from Integer to String
     * @param n It is the type of the question
     * @return The type converted into String
     */
    public static String typeToString (int n) {
        String s = "";
        if (n == 1) s = "Simple";
        if (n == 2) s = "Compuesta";
        if (n == 3) s = "Si o No";
        return s;
    }
    /**
     * Method to get the last Id of the file question
     * @param f File to know from where to get the Id
     * @param numero It is length on bytes of one question
     * @return The Id
     */
    public static int getId(File f, int numero) {
        int id = 0;
        if (f.exists()) {
            try {
                RandomAccessFile fichero = new RandomAccessFile(f, "r");
                while (fichero.length() > 0 ) {
                    fichero.seek(fichero.length()-numero);
                    id = fichero.readInt();
                    break;
                }
                fichero.close();
            }
            catch (Exception e) {
            }
        }
        return id+1;
    }
    /**
     * Method to get the file of the answer
     * @param type It is the number to get the correct File
     * @return The file of the answer
     */
    public static File getFile(int type) {
        File fr = null;
        if (type == 1) fr = new File("respSimple.dat");
        if (type == 2) fr = new File("respComp.dat");
        if (type == 3) fr = new File("respSiNo.dat");
        return fr;
    }
    
    /**
     * Method to calculate the size of the String to write into the File
     * @param palabra It is the String to write in the file
     * @param space It is the predefined space to write the String
     * @return The size of the String
     */
    public static String getSpace(String palabra, int space) {
        byte[] bytes = palabra.getBytes();
        int realSpace = bytes.length - palabra.length() ;
        space = space - realSpace;
        String s = "%"+ space +"s";
        return s;
    }
    /**
     * Method to know the number of bytes of every type of answer
     * @param type It is the type of the answer
     * @return The number of bytes
     */
    public static int getNumberBytes(int type) {
        int n = 0;
        if (type == 1) n = 47; //Cada respuesta simple mide 27 bytes en el fichero
        if (type == 2) n = 107; //Cada respuesta compuesta son 57 bytes en el fichero
        if (type == 3) n = 9; //Cada respuesta si o no son 9 Bytes en el fichero
        return n;
    }
    /**
     * Method to get the type of the Answer
     * @param type It is the type of the answer
     * @return The correct type of Respuesta
     */
    public static Respuestas getType(int type) {
        Respuestas r = null;
        if (type == 1) r = new Simple();
        if (type == 2) r = new Compuesta();
        if (type == 3) r = new Sino();
        return r;
    }
    /**
     * Method to write the question into the file
     * @param f It is the file
     * @param id It is the Id
     * @param p It is the question
     */
    public static void saveQuestion (File f, int id, Preguntas p) {
        id = id-1;
        try {
            if (f.exists()) {
                RandomAccessFile raf = new RandomAccessFile(f, "rw");
                raf.seek(id*Preguntas.getSpace());
                p.saveToFile(raf);
                raf.close();
            }
        }
        catch (Exception e) {
        }
    }
    /**
     * Method to write the answer in the file
     * @param fr It is the file
     * @param id It is the Id
     * @param longitud It is the number of bytes
     * @param r It is the answer
     */
    public static void saveAnswer (File fr, int id, int longitud, Respuestas r) {
        id = id-1;
        try {
            if (fr.exists()) {
                RandomAccessFile fichero = new RandomAccessFile(fr, "rw");
                fichero.seek(id*longitud);
                r.saveToFile(fichero);
                fichero.close();
            }
        }
        catch (Exception e) {
        }
    }
    
    /**
     * Method to read the question from the file
     * @param f It is the file
     * @param id It is the Id
     * @return The question
     */
    public static Preguntas readQuestion (File f, int id) {
        id = id-1;
        Preguntas p = new Preguntas();
        try {
            if (f.exists()) {
                RandomAccessFile fichero = new RandomAccessFile(f, "r");
                fichero.seek(id*Preguntas.getSpace());
                p.loadFromFile(fichero);
                fichero.close();
            }
        }
        catch (Exception e) {
        }
        return p;
    }
    
    /**
     * Method to read the answer in the file
     * @param fr It is the file
     * @param id It is the Id
     * @param longitud It is the number of bytes
     * @return The answer
     */
    public static Respuestas readAnswer (File fr, int id, int longitud) {
        id = id-1;
        Respuestas r = new Respuestas();
        try {
            if (fr.exists()) {
                RandomAccessFile fichero = new RandomAccessFile(fr, "r");
                fichero.seek(id*longitud);
                r.loadFromFile(fichero);
                fichero.close();
            }
        }
        catch (Exception e) {
        }
        return r;
    }
    
    /**
     * Method to create an Alert for the interface
     * @param s It is the String to appear in the alert
     */
    public static void getAlertInfo(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
    /**
     * Method to exit the menu oneplayer or Twoplayer
     * @param s It is the String that will appear in the alert
     * @return The button to know if the player click ok or cancel
     */
    public static boolean getAlertConf(String s) {
        boolean confirm = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(s);
        Optional <ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            confirm = true;
        }
        return confirm;
    }
    
    /**
     * Method to rid of the accent of a String
     * @param s It is the question or answer to get rid of
     * @return The phrase without accents
     */
    public static String ridAccent(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[^\\p{ASCII}]", "");
        return s;
    }
    /**
     * Method to change the color of the textfield of the game question
     * @param n It is the category of the question
     * @return The type of color for the question
     */
    public static String getColorText(int n) {
        String id = "";
        if (n == 1) id = "forestgreen;";
        if (n==2) id = "dodgerblue;";
        if (n==3) id = "peru;";
        if (n==4) id = "lightcoral;";
        if (n==5) id = "darkorange;";
        if (n==6) id = "goldenrod;";
        return id; //verde, azul, marron, rosa, naranja, amarillo
    }
    /**
     * Method to play the winner's music
     */
    public static void musicWinner () {
        String uriString = new File("final_fantasy_victory.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.play();
    }
}
