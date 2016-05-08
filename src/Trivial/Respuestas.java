package Trivial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.RandomAccessFile;

/**
 * Class of Respuestas for the game
 * @author AntoIba
 */
public class Respuestas {
    int id;
    String answer;
    boolean del;
    boolean correcto;
    /**
     * Constructor of the class
     */
    public Respuestas() {
        id = 0;
        answer = "Desconocida";
        del = false;
    }
    /**
     * Constructor with parameters of the class
     * @param id It is the id of the answer
     * @param answer It is the answer
     */
    public Respuestas(int id, String answer) {
        this.id = id;
        this.answer = answer;
        this.del = false;
    }
    /**
     * Method to get the id of the answer
     * @return the id of the answer
     */
    public int getId() {
        return id;
    }
    /**
     * Method to set the id of the answer
     * @param id It is the id of the answer
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Method to get the answer
     * @return The answer
     */
    public String getAnswer() {
        return answer;
    }
    
    /**
     * Method to set the answer
     * @param answer It is the answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    /**
     * Method to know if the answer is deleted
     * @return The boolean to know if it is deleted
     */
    public boolean isDel() {
        return del;
    }
    
    /**
     * Method to set if the answer is deleted
     * @param del It is the boolean to set deleted the answer
     */
    public void setDel(boolean del) {
        this.del = del;
    }
    /**
     * Method to save a Answer into the file
     * @param dat It is the stream of the data
     */
    public void saveToFile(DataOutputStream dat) {
        
        try {
            dat.writeInt(id);
            dat.writeUTF(answer);
            dat.writeBoolean(del);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to load from file the Answer
     * @param dis It is the stream of data
     */
    public void loadFromFile(DataInputStream dis) {
        
        try {
            id = dis.readInt();
            answer = dis.readUTF().trim();
            del = dis.readBoolean();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to save to file the Answer
     * @param raf It is the stream of data
     */
    public void saveToFile(RandomAccessFile raf) {
        try {
            raf.writeInt(id);
            raf.writeUTF(answer);
            raf.writeBoolean(del);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to load from file the Answer
     * @param raf It is the stream of data
     */
    public void loadFromFile(RandomAccessFile raf) {
        
        try {
            id = raf.readInt();
            answer = raf.readUTF().trim();
            del = raf.readBoolean();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
