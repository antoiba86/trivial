package Trivial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Class with method to manipulate the data of the game
 * @author AntoIba86
 */
public class Trivial {
    static private ArrayList<Integer> usedID = new ArrayList<>();
    static boolean responder = true;
    static boolean responderTwo = true;
    static int turno = 1;
    static int numero = 0;
    static int numeroTwo = 0;
    static boolean wait = false;
    static File f = new File ("Preguntas.dat");
    
    /**
     * Method to add a question to a file
     * @param cat It is the category of the question
     * @param type It is the type of the question
     * @param pregunta It is the question of the question
     * @param respuesta It is the answer of the question
     */
    public static void add(int cat, int type, String pregunta, String respuesta) {
        int id, id_ans;
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
        }
    }
    /**
     * Method to read the question from the file to the table
     */
    public static void read() {
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
                    Tabla t = new Tabla(p.getId(), Library.categToString(p.getCat()), Library.typeToString(p.getType())
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
    
    /**
     * Method to search a question from the file
     * @param id It is the id of the question
     * @return The question
     */
    public static String search(int id) {
        String s = "";
        String respuesta = "";
        if (f.exists()) {
            Preguntas p = Library.readQuestion(f, id);
            s = Library.categToString(p.getCat()) + "-" + Library.typeToString(p.getType()) + "-" + p.getQuestion() + "-" + p.isDel() + "-";
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
    
    /**
     * Method to modify a question
     * @param id Id of the question
     * @param cat The category of the question
     * @param type The type of the question
     * @param pregunta The question of the question
     * @param respuesta The answer of the question
     */
    public static void toModify(int id, int cat, int type, String pregunta, String respuesta) {
        File fr = Library.getFile(type);
        int n = Library.getNumberBytes(type);
        Preguntas p = Library.readQuestion(f, id);
        Respuestas r = Library.readAnswer(fr, p.getId_ans(), n);
        p.setCat(cat);
        p.setQuestion(pregunta);
        r.setAnswer(respuesta);
        Library.saveQuestion(f, id, p);
        Library.saveAnswer(fr, p.getId_ans(), n, r);
    }
    /**
     * Method to delete or recover a question 
     * @param id It is the id of the question
     * @param opcion There is two options, delete or recover<br>
     * Number 1 deletes the question and it does not appear in the game<br>
     * Number 2 recovers the question and it appears in the game
     */
    public static void toDelete (int id, int opcion) {
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
        Library.saveAnswer(fr, p.getId_ans(), n, r);
    }
    /**
     * Method to get the question from the file for the game
     * @param number It is the last question Id of the file
     * @return The question from the file
     */
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
    /**
     * Method to recover the question and the answer from the file for the game
     * @param id It is the id of the question
     * @return The question and the answer for the method getPregunta
     */
    public static String gamePregunta(int id) {
        String question;
        String solution;
        Preguntas p = Library.readQuestion(f,id);
        File fr = Library.getFile(p.getType());
        int n = Library.getNumberBytes(p.getType());
        Respuestas r = Library.readAnswer(fr, p.getId_ans(), n);
        question = p.getCat() + "-" + p.getType() + "-" + p.getQuestion();
        solution = question + "-" + r.getAnswer();
        return solution;
    }
    /**
     * Method to reset the elements of the game
     */
    public void reset() {
        usedID.clear();
        responder = true;
        responderTwo = true;
        numero = 0;
        numeroTwo = 0;
        turno = 1;
        wait = false;
    }
    /**
     * Method to return the boolean responder
     * @param opcion It is to get the boolean for the player one or two
     * @return The boolean responder or responerTwo
     */
    public static boolean isResponder(int opcion) {
        boolean r = false;
        if (opcion == 1) r = responder;
        if (opcion == 2) r = responderTwo;
        return r;
    }
    
    /**
     * Method to set the boolean responder to false or true
     * @param responder Boolean to know is the question was answer in the game
     * @param opcion There is two option for the two players
     */
    public static void setResponder(boolean responder, int opcion) {
        if (opcion == 1) Trivial.responder = responder;
        if (opcion == 2) Trivial.responderTwo = responder;
    }
    /**
     * Method to get the score of the game for the two players
     * @param opcion It is the option for the player one or two
     * @return The score for the two players
     */
    public static int getNumero(int opcion) {
        int n = 0;
        if (opcion == 1) n = numero;
        if (opcion == 2) n = numeroTwo;
        return n;
    }
    /**
     * Method to set the score of the game for the two players
     * @param numero It is the score of the player
     * @param opcion It is the option for the player one or two
     */
    public static void setNumero(int numero, int opcion) {
        if (opcion == 1) Trivial.numero = numero;
        if (opcion == 2) Trivial.numeroTwo = numero;
    }
    /**
     * Method to get the turn of the player
     * @return The turn of the player
     */
    public static int getTurno() {
        return turno;
    }
    
    /**
     * Method to set the turn of the player
     * @param turno It is the turn of the player
     */
    public static void setTurno(int turno) {
        Trivial.turno = turno;
    }
    /**
     * Method for the variable wait which is to know when is finish the turn
     * @return The variable wait
     */
    public static boolean isWait() {
        return wait;
    }
    /**
     * Method to set the variable wait
     * @param wait It is the boolean of end of turn
     */
    public static void setWait(boolean wait) {
        Trivial.wait = wait;
    }
}
