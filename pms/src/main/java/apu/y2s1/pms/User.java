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
    private String role;
    private String filename;
    private DataAbstract db;
    
    private User(String ID, String role) {
        this.role = role;
        switch(this.role) {
            case "student":
                this.filename = "Students.txt";
                break;
            case "lecturer":
                this.filename = "Lecturers.txt";
                break;
            case "admin":
                this.filename = "Admins.txt";
                break;
            case "pm":
                this.filename = "ProjectManagers.txt";
                break;
            default:
                break;
        }
        db = new DataAbstract(this.filename);
    }
    

    // This object should be created upon successful login
    // seting default user for the sake of development
    public static User getInstance() {
        if (instance == null) {
            // using default user devid
            instance = new User("U999","student");
       }
        return instance;
    }
    
    private void getUserData(String ID){
        int intID = db.getIndex(userID);
        String[] rawdata = db.getRow(intID);
        setUserID(rawdata[0]);
        
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }

}
