package Trivial;

/**
 * Class to build a table in the game
 * @author AntoIba
 */
public class Tabla {
    int id;
    String cat;
    String type;
    String question;
    String answer;
    boolean del;
    /**
     * Constructor of the class Tabla with parameters
     * @param id It is the id of the question
     * @param cat It is the category of the question
     * @param type It is the type of the question
     * @param question It is the question
     * @param answer It is the answer
     * @param del It is to know if the question is deleted
     */
    public Tabla(int id, String cat, String type, String question, String answer, boolean del) {
        this.id = id;
        this.cat = cat;
        this.type = type;
        this.question = question;
        this.answer = answer;
        this.del = del;
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
     * Method to get the category of the question
     * @return The category of the question
     */
    public String getCat() {
        return cat;
    }
    /**
     * Method to set the category of the question
     * @param cat The category of the question
     */
    public void setCat(String cat) {
        this.cat = cat;
    }
    
    /**
     * Method to get the type of the question
     * @return The type of the question
     */
    public String getType() {
        return type;
    }
    /**
     * Method to get the type of the question
     * @param type The type of the question
     */
    public void setType(String type) {
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
    
}
