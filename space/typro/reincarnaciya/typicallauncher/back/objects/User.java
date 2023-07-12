package space.typro.reincarnaciya.typicallauncher.back.objects;

public class User {
    private final String userName;
    private String showName;
    private int donateBalance;
    private char prefix = '[';
    private char suffix = ']';
    private String role = "Игрок";
    private String donateRole = "Нет донатной роли";

    public User(String userName, String showName){
        this.userName = userName;
        this.showName = showName;
    }
    public User(String userName) {
        this(userName, userName);
    }

    public String getUserName() {
        return userName;
    }
    public String getShowName(){
        return showName;
    }
}
