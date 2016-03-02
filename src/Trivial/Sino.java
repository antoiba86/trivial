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
public class Sino extends Respuestas {
    public Sino(){
        super();
    }
    public Sino(int id, String answer){
        super();
    }
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
