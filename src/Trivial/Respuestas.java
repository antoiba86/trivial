/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.RandomAccessFile;

/**
 *
 * @author DAW13
 */
public class Respuestas {
    int id;
    String answer;
    boolean del;
    boolean correcto;
    public Respuestas() {
        id = 0;
        answer = "Desconocida";
        del = false;
    }
    public Respuestas(int id, String answer) {
        this.id = id;
        this.answer = answer;
        this.del = false;
    }
    
    public void verify(String s) {
        if (answer.equals(s)) correcto = true;
        else correcto = false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }
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
