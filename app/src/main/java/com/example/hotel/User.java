package com.example.hotel;

public class User {
    private String id;
    private String password;
    private String name;
    private int Authority;
    private String CardNum;
    private boolean isLogind;

    private User(){
        id = "";
        password = "";
        name = "workin";
        Authority = 0;
        CardNum = "0000";
        isLogind = false;
    }

    private static User instance;
    public static synchronized User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswod() {
        return password;
    }

    public void setPasswod(String passwod) {
        this.password = passwod;
    }

    public int getAuthority() {
        return Authority;
    }

    public void setAuthority(int authority) {
        Authority = authority;
    }

    public String getCardNum() {
        return CardNum;
    }

    public void setCardNum(String cardNum) {
        CardNum = cardNum;
    }


    public boolean isLogind() {
        return isLogind;
    }

    public void setLogind(boolean logind) {
        isLogind = logind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void Login(String id, String password, String name, int Auth, String cardNum){
        isLogind = true;
        this.id = id;
        this.password = password;
        this.name = name;
        this.Authority = Auth;
        this.CardNum = cardNum;
    }

    public void LogOut(){
        instance = new User();
    }
}
