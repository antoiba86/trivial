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
 * @author Anto
 */
public class Preguntas {
    
    int id;
    int id_ans;
    int cat;
    int type;
    String question;
    boolean del;
    
    
    public Preguntas() {
        id = 0;
        id_ans = 0;
        cat = 0;
        type = 0;
        question = "Desconocida";
        del = false;
    }
    
    public Preguntas(int id, int id_ans, int cat, int type, String question) {
        this.id = id;
        this.id_ans = id_ans;
        this.cat = cat;
        this.type = type;
        this.question = question;
        this.del = false;
    }
    public static int getSpace(){
        int space = 139;
        return space;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId_ans() {
        return id_ans;
    }

    public void setId_ans(int id_ans) {
        this.id_ans = id_ans;
    }
    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }
    
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
