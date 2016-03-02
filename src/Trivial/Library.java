package Trivial;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.Normalizer;
import javafx.scene.control.Alert;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DAW13
 */
public class Library {
    
    public static int convertCateg(String s) {
        int i = 0;
        if (s.contains("Ciencia")) i = 1;
        if (s.contains("Geogra")) i = 2;
        if (s.contains("Arte")) i = 3;
        if (s.contains("Espec")) i = 4;
        if (s.contains("Deportes")) i = 5;
        if (s.contains("Historia")) i = 6;
        return i;
    }
    
    public static String categConvert(int n) {
        String s = "";
        if (n == 1 ) s = "Ciencia y Naturaleza";
        if (n == 2) s = "Geografía";
        if (n == 3) s = "Arte y Literatura";
        if (n == 4) s = "Espectáculos";
        if (n == 5) s = "Deportes";
        if (n == 6) s = "Historia";
        return s;
    }
    
    public static int convertType(String s) {
        int i = 0;
        if (s.contains("Simple")) i = 1;
        if (s.contains("Compuesta")) i = 2;
        if (s.contains("Si o No")) i = 3;
        return i;
    }
    public static String typeConvert (int n) {
        String s = "";
        if (n == 1) s = "Simple";
        if (n == 2) s = "Compuesta";
        if (n == 3) s = "Si o No";
        return s;
    }
    
    public static int convertOpc(String s) {
        int i = 0;
        if (s.contains("Añadir")) i = 1;
        if (s.contains("Modificar")) i = 2;
        return i;
    }
    
    public static int convertShow(String s) {
        int i = 0;
        if (s.contains("Todas")) i = 1;
        if (s.contains("Una")) i = 2;
        return i;
    }
    
    
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
    
    public static File getFile(int type) {
        File fr = null;
        if (type == 1) fr = new File("respSimple.dat");
        if (type == 2) fr = new File("respComp.dat");
        if (type == 3) fr = new File("respSiNo.dat");
        return fr;
    }
    
    public static String getSpace(String palabra, int space) {
        byte[] bytes = palabra.getBytes();
        int realSpace = bytes.length - palabra.length() ;
        space = space - realSpace;
        String s = "%"+ space +"s";
        return s;
    }
    
    public static int getNumberBytes(int type) {
        int n = 0;
        if (type == 1) n = 47; //Cada respuesta simple mide 27 bytes en el fichero
        if (type == 2) n = 107; //Cada respuesta compuesta son 57 bytes en el fichero
        if (type == 3) n = 9; //Cada respuesta si o no son 9 Bytes en el fichero
        return n;
    }
    
    public static Respuestas getType(int type) {
        Respuestas r = null;
        if (type == 1) r = new Simple();
        if (type == 2) r = new Compuesta();
        if (type == 3) r = new Sino();
        return r;
    }
    
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
    public static void saveAnswer (File fr, int id, int longitud, Respuestas r) {
        id = id-1;
        try {
            if (fr.exists()) {
                RandomAccessFile fichero = new RandomAccessFile(fr, "r");
                fichero.seek(id*longitud);
                r.saveToFile(fichero);
                fichero.close();
            }
        }
        catch (Exception e) {
        }
    }
    
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
    
    public static void getAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
    
    public static String ridAccent(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[^\\p{ASCII}]", "");
        return s;
    }
    
}
