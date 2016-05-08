package Trivial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.RandomAccessFile;

/**
 * Class to save to file and load from file the Questions
 * @author Anto
 */
public class Preguntas {
    
    int id;
    int id_ans;
    int cat;
    int type;
    String question;
    boolean del;
    
    /**
     * Constructor of the class Preguntas
     */
    public Preguntas() {
        id = 0;
        id_ans = 0;
        cat = 0;
        type = 0;
        question = "Desconocida";
        del = false;
    }
    
    /**
     * Constructor of the class Preguntas with parameters
     * @param id It is the id of the question
     * @param id_ans It is the id of the answer
     * @param cat It is the category of the question
     * @param type It is the type of the question
     * @param question It is the String of the question
     */
    public Preguntas(int id, int id_ans, int cat, int type, String question) {
        this.id = id;
        this.id_ans = id_ans;
        this.cat = cat;
        this.type = type;
        this.question = question;
        this.del = false;
    }
    /**
     * Method to return the number of bytes of every question in the file
     * @return The number of bytes
     */
    public static int getSpace(){
        int space = 139;
        return space;
    }
    /**
     * Method to get the id of the question
     * @return The id of the question
     */
    public int getId() {
        return id;
    }
    /**
     * Method to set the id of the question
     * @param id Id of the question
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Method to get the id of the answer
     * @return The id of the answer
     */
    public int getId_ans() {
        return id_ans;
    }
    /**
     * Method to set the id of the answer
     * @param id_ans It is the id of the answer
     */
    public void setId_ans(int id_ans) {
        this.id_ans = id_ans;
    }
    /**
     * Method to get the category of the question
     * @return The category of the question
     */
    public int getCat() {
        return cat;
    }
    /**
     * Method to set the category of the question
     * @param cat The category of the question
     */
    public void setCat(int cat) {
        this.cat = cat;
    }
    /**
     * Method to get the type of the question
     * @return The type of the question
     */
    public int getType() {
        return type;
    }
    /**
     * Method to get the type of the question
     * @param type The type of the question
     */
    public void setType(int type) {
        this.type = type;
    }
    /**
     * Method to get the String of the question
     * @return The String of the question
     */
    public String getQuestion() {
        return question;
    }
    /**
     * Method to get the question
     * @param question The question
     */
    public void setQuestion(String question) {
        this.question = question;
    }
    /**
     * Method to know if the question is delete
     * @return The boolean delete of the question
     */
    public boolean isDel() {
        return del;
    }
    /**
     * Method to set if the question is delete or not
     * @param del The boolean to know if it is delete
     */
    public void setDel(boolean del) {
        this.del = del;
    }
    /**
     * Method to save to file the Question
     * @param raf It is the stream of data
     */
    public void saveToFile(RandomAccessFile raf) {
        int space = 120;
        String s = Library.getSpace(question, space);
        try {
            raf.writeInt(id);
            raf.writeInt(id_ans);
            raf.writeInt(cat);
            raf.writeInt(type);
            raf.writeUTF(String.format(s, question));
            raf.writeBoolean(del);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method to load from file the Question
     * @param raf It is the stream of data
     */
    public void loadFromFile(RandomAccessFile raf) {
        
        try {
            id = raf.readInt();
            id_ans = raf.readInt();
            cat = raf.readInt();
            type = raf.readInt();
            question = raf.readUTF().trim();
            del = raf.readBoolean();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method to save a Question into the file
     * @param dat It is the stream of the data
     */
    public void saveToFile(DataOutputStream dat) {
        int space = 120;
        String s = Library.getSpace(question, space);
        try {
            dat.writeInt(id);
            dat.writeInt(id_ans);
            dat.writeInt(cat);
            dat.writeInt(type);
            dat.writeUTF(String.format(s, question));
            dat.writeBoolean(del);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to load from file the Question
     * @param dis It is the stream of data
     */
    public void loadFromFile(DataInputStream dis) {
        
        try {
            id = dis.readInt();
            id_ans = dis.readInt();
            cat = dis.readInt();
            type = dis.readInt();
            question = dis.readUTF().trim();
            del = dis.readBoolean();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
 }
