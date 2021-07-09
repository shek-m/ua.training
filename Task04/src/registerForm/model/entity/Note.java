package registerForm.model.entity;

/**
 * Created by MShekera on 04.07.2021
 */
public class Note {
    private String name;
    private String nickname;

    //Constructors
    public Note(){}

    public Note(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}