/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu.y2s1.pms;

/**
 *
 * @author Jeslyn
 */
public class User {
    private static User instance;
    private String userID;
    private String username;
    
    private User() {
        
    }
    
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
       }
        return instance;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
}
