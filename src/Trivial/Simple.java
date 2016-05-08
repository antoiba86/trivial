package Trivial;

import java.io.DataOutputStream;
import java.io.RandomAccessFile;
/**
 * Class of the son of Respuestas, Simple
 * @author AntoIba
 */
public class Simple extends Respuestas{
    
    /**
     * Constructor of the class
     */
    public Simple() {
        super();
    }
    /**
     * Constructor with parameters of the class
     * @param id It is the id of the answer
     * @param answer It is the answer
     */
    public Simple(int id, String answer) {
        super();
    }
    /**
     * Method to save a Answer into the file
     * @param dat It is the stream of the data
     */
    @Override
    public void saveToFile(DataOutputStream dat) {
        int space = 40;
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
        int space = 40;
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
