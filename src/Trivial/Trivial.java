package Trivial;

import static Trivial.Interface.addTabla;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DAW13
 */
public class Trivial {
    private ArrayList<Integer> usedID = new ArrayList<>();
    static boolean responder = true;
    static boolean responderTwo = true;
    static boolean turno = true;
    static boolean delText = false;
    static int numero = 0;
    static int numeroTwo = 0;
    static int numPreg = 0;
    
    
    
    public static void add(int cat, int type, String pregunta, String respuesta) {
        int id, id_ans;
        File f = new File("Preguntas.dat");
        File fr = null;
        Respuestas r = null;
        if (type == 1) r = new Simple();
        if (type == 2) r = new Compuesta();
        if (type == 3) r = new Sino();
        fr = Library.getFile(type);
        int n = Library.getNumberBytes(type);
        id = Library.getId(f, Preguntas.getSpace()); //Cada pregunta mide 137 Bytes en el fichero
        id_ans = Library.getId(fr, n);
        // Crea un objeto Preguntas con los datos introducidos por teclado
        Preguntas preguntas = new Preguntas(id, id_ans, cat, type, pregunta);
        r.setId(id_ans);
        r.setAnswer(respuesta);
        try {
            // Abre el flujo de salida y escribe la pregunta en el fichero
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(f, true));
            preguntas.saveToFile(dos);
            dos.close();
            // Abre el flujo de salida y escribe la respuesta en el fichero
            DataOutputStream dos2 = new DataOutputStream(new FileOutputStream(fr, true));
            r.saveToFile(dos2);
            dos2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void read() {
        File f = new File ("Preguntas.dat");
        Preguntas p = new Preguntas();
        Respuestas r;
        String respuesta = "";
        try {
            if (f.exists()) {
                FileInputStream finput = new FileInputStream(f);
                DataInputStream fichero = new DataInputStream(finput);
                while (fichero.available() > 0) {
                    p.loadFromFile(fichero);
                    r = Library.readAnswer(Library.getFile(p.getType()), p.getId_ans(), Library.getNumberBytes(p.getType()));
                    if (p.getType() == 2) {
                        String[] answer = r.getAnswer().split("_");
                        for (int i = 0; i < answer.length; i++) {
                            if (i == 3) respuesta = respuesta + "Correcta. " + answer[i];
                            else respuesta = respuesta + (i+1) + ". " + answer[i] + " ";
                        }
                    }
                    else respuesta = r.getAnswer();
                    Tabla t = new Tabla(p.getId(), Library.categConvert(p.getCat()), Library.typeConvert(p.getType())
                    ,p.getQuestion(), respuesta, p.isDel());
                    Interface.addTabla(p.getId(),t.getCat(), t.getType(),t.getQuestion(),t.getAnswer(),t.isDel());
                    respuesta = "";
                }
                fichero.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Tabla lookFor(int id, File f) {
        Tabla t = null;
        String s;
        if (f.exists()) {
            Preguntas p = Library.readQuestion(f, id);
            Respuestas r = Library.readAnswer(Library.getFile(p.getType()), p.getId_ans(), Library.getNumberBytes(p.getType()));
            t = new Tabla(id, Library.categConvert(p.getCat()), Library.typeConvert(p.getType())
            ,p.getQuestion(), r.getAnswer(), p.isDel());
        }
        return t;
    }
    //Look for y GamePregunta es igual
    public static String lookFor(int id) {
        String s = "";
        String respuesta = "";
        File f = new File ("Preguntas.dat");
        if (f.exists()) {
            Preguntas p = Library.readQuestion(f, id);
            s = Library.categConvert(p.getCat()) + "-" + Library.typeConvert(p.getType()) + "-" + p.getQuestion() + "-" + p.isDel() + "-";
            Respuestas r = Library.readAnswer(Library.getFile(p.getType()), p.getId_ans(), Library.getNumberBytes(p.getType()));
            if (p.getType() == 2) {
                String[] answer = r.getAnswer().split("_");
                for (int i = 0; i < answer.length; i++) {
                    if (i == 3) respuesta = respuesta + "Correcta. " + answer[i];
                    else respuesta = respuesta + (i+1) + ". " + answer[i] + " ";
                }
            }
            else respuesta = r.getAnswer();
            s = s + respuesta;
        }
        return s;
    }
    
    public static void toModify(int id, int cat, int type, String pregunta, String respuesta) {
        File f = new File("Preguntas.dat");
        File fr = Library.getFile(type);
        int n = Library.getNumberBytes(type);
        Preguntas p = Library.readQuestion(f, id);
        Respuestas r = Library.readAnswer(fr, p.getId_ans(), n);
        p.setCat(cat);
        p.setQuestion(pregunta);
        r.setAnswer(respuesta);
        Library.saveQuestion(f, id, p);
        Library.saveAnswer(fr, id, n, r);
    }
    
    public static void toDelete (int id, int opcion) {
        File f = new File("Preguntas.dat");
        Preguntas p = Library.readQuestion(f, id);
        File fr = Library.getFile(p.getType());
        int n = Library.getNumberBytes(p.getType());
        Respuestas r = Library.readAnswer(fr, p.getId_ans(), n);
        if (opcion == 1) {
            p.setDel(true);
            r.setDel(true);
        }
        else {
           p.setDel(false);
           r.setDel(false); 
        }
        
        Library.saveQuestion(f, id, p);
        Library.saveAnswer(fr, id, n, r);
    }
    
    public String getPregunta(int number){
        number -= 1;
        boolean inside = true;
        int n = 0;
        if (usedID.isEmpty()) {
            inside = false;
            n = ((int)(Math.random()*number+1));
        }
        while (inside) {
            n = (int)(Math.random()*number+1);
            for (int i = 0; i < usedID.size(); i++) {
                if (n == usedID.get(i)) {
                    inside = true;
                    break;
                }
                else inside = false;
            }
        }
        usedID.add(n);
        String s = Trivial.gamePregunta(n);
        return s;
    }
    
    public static String gamePregunta(int id) {
        String s = "";
        String solucion = "";
        File f = new File ("Preguntas.dat");
        Preguntas p = Library.readQuestion(f,id);
        File fr = Library.getFile(p.getType());
        int n = Library.getNumberBytes(p.getType());
        Respuestas r = Library.readAnswer(fr, p.getId_ans(), n);
        s = String.format("%20s %10s %100s " , Library.categConvert(p.getCat()), Library.typeConvert(p.getType()), p.getQuestion()) + "\n";
        solucion = s + "-" + r.getAnswer();
        return solucion;
    }
    
    public void reset() {
        usedID.clear();
        responder = true;
        responderTwo = true;
        numero = 0;
        numeroTwo = 0;
        numPreg = 0;
        turno = true;
        delText = true;
    }
    
    public void setUsedID(ArrayList<Integer> usedID) {
        this.usedID = usedID;
    }

    public static boolean isResponder(int opcion) {
        boolean r = false;
        if (opcion == 1) r = responder;
        if (opcion == 2) r = responderTwo;
        return r;
    }

    public static void setResponder(boolean responder, int opcion) {
        if (opcion == 1) Trivial.responder = responder;
        if (opcion == 2) Trivial.responderTwo = responder;
    }

    public static int getNumero(int opcion) {
        int n = 0;
        if (opcion == 1) n = numero;
        if (opcion == 2) n = numeroTwo;
        return numero;
    }

    public static void setNumero(int numero, int opcion) {
        if (opcion == 1) Trivial.numero = numero;
        if (opcion == 2) Trivial.numeroTwo = numero;
    }
    //Para que era??????
    public static int getNumPreg() {
        return numPreg;
    }

    public static void setNumPreg(int numPreg) {
        Trivial.numPreg = numPreg;
    }

    public static boolean isTurno() {
        return turno;
    }

    public static void setTurno(boolean turno) {
        Trivial.turno = turno;
    }

    public static boolean isDelText() {
        return delText;
    }

    public static void setDelText(boolean delText) {
        Trivial.delText = delText;
    }
    
    
    
}
