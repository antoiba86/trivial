package Trivial;

import java.io.DataOutputStream;
import java.io.RandomAccessFile;

/**
 * Class to manipulate the Answer Compuestas
 * @author AntoIba
 */
public class Compuesta extends Respuestas {
    
    /**
     * Constructor of the class Compuesta
     */
    public Compuesta() {
        super();
    }
    /**
     * Constructor of the class with parameters
     * @param id It is the ID of the answer
     * @param answer It is the String of the answer
     */
    public Compuesta(int id, String answer) {
        super();
    }
    /**
     * Method to save to file the Answer
     * @param dat It is the stream of the data 
     */
    @Override
    public void saveToFile(DataOutputStream dat) {
        int space = 100;
        String s = Library.getSpace(answer, space);
        try {
            dat.writeInt(id);
            dat.writeUTF(String.format(s,answer));
            dat.writeBoolean(del);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to save to file the Answer 
     * @param raf It is the stream of data
     */
    @Override
    public void saveToFile(RandomAccessFile raf) {
        int space = 100;
        String s = Library.getSpace(answer, space);
        try {
            raf.writeInt(id);
            raf.writeUTF(String.format(s,answer));
            raf.writeBoolean(del);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
