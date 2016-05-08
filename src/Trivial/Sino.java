package Trivial;

import java.io.DataOutputStream;
import java.io.RandomAccessFile;

/**
 * Class of the son of Respuestas, Sino
 * @author AntoIba
 */
public class Sino extends Respuestas {
    
    /**
     * Constructor of the class
     */
    public Sino(){
        super();
    }
    /**
     * Constructor with parameters of the class
     * @param id It is the id of the answer
     * @param answer It is the answer
     */
    public Sino(int id, String answer){
        super();
    }
    /**
     * Method to save a Answer into the file
     * @param dat It is the stream of the data
     */
    @Override
    public void saveToFile(DataOutputStream dat) {
        
        try {
            dat.writeInt(id);
            dat.writeUTF(String.format("%2s",answer));
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
        
        try {
            raf.writeInt(id);
            raf.writeUTF(String.format("%2s",answer));
            raf.writeBoolean(del);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
