/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivial;

import java.io.DataOutputStream;
import java.io.RandomAccessFile;




/**
 *
 * @author DAW13
 */
public class Simple extends Respuestas{
    
    public Simple() {
        super();
    }
    public Simple(int id, String answer) {
        super();
    }
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
