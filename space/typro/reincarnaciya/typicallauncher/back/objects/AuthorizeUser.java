package space.typro.reincarnaciya.typicallauncher.back.objects;

import java.util.HashMap;

public class AuthorizeUser {
    public static final AuthorizeUser NULL_AUTHORIZE_USER = new AuthorizeUser("", null);
    private final String passWord;
    private HashMap<String, String> webAnswer;
    private boolean authorized;
    private final User user;

    public AuthorizeUser(String passWord, User user){
        this.passWord = passWord;
        this.user = user;
        authorized = false;
    }


    public boolean Authorize(){
        authorized = (user.getUserName().equals("reincarnaciya") && passWord.equals("132213")) || (user.getUserName().equals("test") && passWord.equals("testPass"));
        return authorized;
    }
    public boolean answerContains(String key){
        return webAnswer.containsKey(key);
    }
    public String getPassWord() {
        return passWord;
    }
    public boolean isAuthorized() {
        return authorized;
    }
    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthorizeUser otherUser = (AuthorizeUser) obj;
        User thisUser = this.getUser();
        User otherUserObj = otherUser.getUser();
        if (thisUser == null || otherUserObj == null) {
            return false;
        }
        return thisUser.getUserName().equals(otherUserObj.getUserName());
    }

    @Override
    public String toString() {
        return "AuthorizeUser{" + "webAnswer=" + webAnswer +
                ", authorized=" + authorized +
                '}';
    }
}
