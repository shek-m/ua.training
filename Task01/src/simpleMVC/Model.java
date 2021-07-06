package simpleMVC;

/**
 * created by MS on 24.06.2021
 */

public class Model {
    private String value = "";

    //The program logic

    /**
     * this method constructs the String "Hello world!" from user's input strings.
     * @param str - already validated string: either "Hello" or "world!" depending on the number
     */
    public void constructString(String str){
        value +=str;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String answer) {
        this.value = answer;
    }
}